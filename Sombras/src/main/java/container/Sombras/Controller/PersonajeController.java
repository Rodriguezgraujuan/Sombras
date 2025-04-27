package container.Sombras.Controller;

import container.Sombras.Entidad.Personaje;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Servicio.UsuarioService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonajeController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/createCharacter")
    public void createCharacter(@RequestBody Personaje personaje) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario;
        if (authentication.getPrincipal() instanceof OAuth2User) {
            usuario = usuarioService.findByEmail(((OAuth2User) authentication.getPrincipal()).getAttribute("email"));
        }else {
            usuario = usuarioService.findByEmail(authentication.getName());
        }
        if (personaje.getNombre().isEmpty()||personaje.getApellido().isEmpty()||personaje.getNivel()<0) {
            throw new IllegalArgumentException("Nombre, Apellido o nivel no valido. Debe de rellenar el nombre, personaje y el nivel > 0");
        }

        String imagenCharacter = "prueba";
        Personaje newPersonaje = new Personaje(personaje.getNombre(), personaje.getApellido(), personaje.getClase(), personaje.getRaza(), personaje.getDescripcion(), imagenCharacter, personaje.getNivel());
    }
}
