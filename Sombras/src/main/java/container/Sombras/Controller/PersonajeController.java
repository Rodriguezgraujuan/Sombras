package container.Sombras.Controller;

import container.Sombras.Entidad.Personaje;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.Raza_AtributoRepository;
import container.Sombras.Servicio.*;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createCharacter(@RequestBody Personaje personaje) {
        Usuario usuario = obtenerUsuarioAutenticado();

        if (personaje.getNombre().isEmpty() || personaje.getApellido().isEmpty() || personaje.getNivel() < 0) {
            throw new IllegalArgumentException("Nombre, Apellido o nivel no válido.");
        }

        Personaje newPersonaje = new Personaje(
                personaje.getNombre(),
                personaje.getApellido(),
                claseService.findById(personaje.getClase().getId()),
                razaService.findById(personaje.getRaza().getId()),
                personaje.getDescripcion(),
                "prueba",
                personaje.getNivel(),
                usuario
        );
        newPersonaje.setUsuario(usuario); // Asociar al usuario
        personajeService.save(newPersonaje);
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
            if (personaje.isPulico() && !personaje.getUsuario().equals(usuario)) {
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

    @Transactional
    @PostMapping("/deleteCharacter")
    public void deleteCharacter(@RequestBody Personaje personajeRequest) {
        Usuario usuario = obtenerUsuarioAutenticado();
        Personaje personaje = personajeService.findById(personajeRequest.getId());

        if (personaje.getUsuario().getId()!=usuario.getId()) {
            throw new IllegalArgumentException("El personaje no es del usuario.");
        }

        personajeService.delete(personaje);
    }

    @Transactional
    @PostMapping("/editLevels")
    public void getCharacterLevels(@RequestBody Personaje personajeRequest) throws BadRequestException {
        Usuario usuario = obtenerUsuarioAutenticado();
        Personaje personaje = personajeService.findById(personajeRequest.getId());

        if (personaje.getUsuario().getId()!=usuario.getId()) {
            throw new IllegalArgumentException("El personaje no es del usuario.");
        }

        int puntosBase = switch (personaje.getRaza().getName()) {
            case "Humano" -> 6;
            case "Semielfo" -> 3;
            case "Elfo" -> 5;
            case "Gnomo" -> 4;
            case "Siemorco" -> 3;
            case "Enano" -> 3;
            default -> 0;
        };

        int puntosRestantes = (personajeRequest.getNivel() * 2) - (
                personajeRequest.getConstitucion() +
                        personajeRequest.getFuerza() +
                        personajeRequest.getDestreza() +
                        personajeRequest.getInteligencia() +
                        personajeRequest.getSabiduria()
        ) + puntosBase;

        if (puntosRestantes >= 0) {
            personaje.setConstitucion(personajeRequest.getConstitucion());
            personaje.setFuerza(personajeRequest.getFuerza());
            personaje.setDestreza(personajeRequest.getDestreza());
            personaje.setInteligencia(personajeRequest.getInteligencia());
            personaje.setSabiduria(personajeRequest.getSabiduria());
            personaje.setNivel(personajeRequest.getNivel());
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
