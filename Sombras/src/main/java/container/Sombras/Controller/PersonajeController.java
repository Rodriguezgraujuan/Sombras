package container.Sombras.Controller;

import container.Sombras.Dto.PersonajeDto;
import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Personaje;
import container.Sombras.Entidad.Raza;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.Raza_AtributoRepository;
import container.Sombras.Servicio.*;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonajeController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private ClaseService claseService;
    @Autowired
    private RazaService razaService;
    @Autowired
    private PersonajeService personajeService;
    @Autowired
    private Raza_AtributoRepository raza_AtributoRepository;
    @Autowired
    private AtributoService atributoService;

    @Transactional
    @PostMapping("/createCharacter")
    public ResponseEntity<String> createCharacter(@RequestBody PersonajeDto personajeDto) {
        System.out.println("Entró al método");
        Personaje personaje = new Personaje();
        Usuario usuario = obtenerUsuarioAutenticado();
        if (personajeDto.getNombre().isEmpty() || personajeDto.getApellido().isEmpty() || personajeDto.getNivel() < 0) {
            throw new IllegalArgumentException("Nombre, Apellido o nivel no válido.");
        }
        System.out.println("PERSONAJE DTO:"+personajeDto.toString());
        Personaje newPersonaje = new Personaje(
                personajeDto.getNombre(),
                personajeDto.getApellido(),
                claseService.findById(personajeDto.getClase()),
                razaService.findById(personajeDto.getRaza()),
                personajeDto.getDescripcion(),
                "prueba",
                personajeDto.getNivel(),
                usuario
        );
        newPersonaje.setPublico(personajeDto.isPublico());
        newPersonaje.setUsuario(usuario);
        personajeService.save(newPersonaje);
        return ResponseEntity.ok("Personaje creada correctamente");
    }

    @GetMapping("/showCharacter")
    public List<List<String>> showCharacter() {
        Usuario usuario = obtenerUsuarioAutenticado();

        List<List<String>> lista = new ArrayList<>();
        for (Personaje personaje : usuario.getPersonajes()) {
            List<String> datos = new ArrayList<>();
            datos.add(personaje.getId().toString());
            datos.add(personaje.getNombre());
            datos.add(personaje.getClase().getNombre());
            datos.add(personaje.getImagen());
            datos.add(String.valueOf(personaje.isPublico()));
            lista.add(datos);
        }

        return lista;
    }

    @GetMapping("/showOtherCharacter")
    public List<List<String>> showOtherCharacter() {
        Usuario usuario = obtenerUsuarioAutenticado();

        List<List<String>> lista = new ArrayList<>();

        List<Personaje> personajes = personajeService.findAll();  // Si tienes un servicio que obtiene todos los personajes

        for (Personaje personaje : personajes) {
            if (personaje.isPublico() && !personaje.getUsuario().equals(usuario)) {
                List<String> datos = new ArrayList<>();
                datos.add(personaje.getId().toString());
                datos.add(personaje.getNombre());
                datos.add(personaje.getClase().getNombre());
                datos.add(personaje.getImagen());
                lista.add(datos);
            }
        }

        return lista;
    }

    @PostMapping("/deleteCharacter")
    public ResponseEntity<String> deleteCharacter(@RequestBody Personaje personajeRequest) {
        Usuario usuario = obtenerUsuarioAutenticado();
        Personaje personaje = personajeService.findById(personajeRequest.getId());

        if (personaje.getUsuario().getId()!=usuario.getId()) {
            throw new IllegalArgumentException("El personaje no es del usuario.");
        }
        Clase clase = personaje.getClase();
        Raza raza = personaje.getRaza();

        if (clase != null) {
            clase.getPersonajes().remove(personaje);
        }

        if (raza != null) {
            raza.getPersonaje().remove(personaje);
        }

        personajeService.delete(personaje);

        return ResponseEntity.ok("Personaje eliminado correctamente");
    }

    @GetMapping("/personajeData")
    public Personaje getCharacterLevel(@RequestParam Long personajeId) {
        return personajeService.findById(personajeId);
    }

    @Transactional
    @PostMapping("/editLevels")
    public void postCharacterLevels(@RequestBody Personaje personajeRequest) throws BadRequestException {
        Usuario usuario = obtenerUsuarioAutenticado();
        Personaje personaje = personajeService.findById(personajeRequest.getId());

        if (personaje.getUsuario().getId()!=usuario.getId()) {
            throw new IllegalArgumentException("El personaje no es del usuario.");
        }

        int puntosBase = switch (personaje.getRaza().getName()) {
            case "Humano" -> 6;
            case "Semielfo", "Siemorco", "Enano" -> 3;
            case "Elfo" -> 5;
            case "Gnomo" -> 4;
            default -> 0;
        };
        int puntosRestantes = (personaje.getNivel() * 2 + puntosBase) - (
                personajeRequest.getConstitucion() +
                        personajeRequest.getFuerza() +
                        personajeRequest.getDestreza() +
                        personajeRequest.getInteligencia() +
                        personajeRequest.getSabiduria());

        if (puntosRestantes >= 0) {
            personaje.setConstitucion(personajeRequest.getConstitucion());
            personaje.setFuerza(personajeRequest.getFuerza());
            personaje.setDestreza(personajeRequest.getDestreza());
            personaje.setInteligencia(personajeRequest.getInteligencia());
            personaje.setSabiduria(personajeRequest.getSabiduria());
            personaje.setNivel(personaje.getNivel());
            personajeService.save(personaje);
        } else {
            throw new BadRequestException("Puntos de nivel inválidos.");
        }
    }

    private Usuario obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof OAuth2User) {
            return usuarioService.findByEmail(((OAuth2User) auth.getPrincipal()).getAttribute("email"));
        } else {
            return usuarioService.findByEmail(auth.getName());
        }
    }
}
