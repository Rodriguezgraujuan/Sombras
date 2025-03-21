package container.Sombras.config;

import container.Sombras.Entidad.Personaje;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Entidad.Usuario_Personaje;
import container.Sombras.Servicio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class DefaultUserPersonajeData {

    @Bean
    @Order(12)
    CommandLineRunner initUsuarioPlayersData(PersonajeService personajeService, ClaseService claseService, RazaService razaService, UsuarioService usuarioService, Usuario_PersonaService usuario_PersonaService){
        return args -> {
                Usuario usuario = usuarioService.findByEmail("admin@gmail.com");
            if (!(usuario_PersonaService.findAll().size() >0)) {
                Set<Personaje> personajesPorDefecto = new HashSet<>(personajeService.findAll());
                personajesPorDefecto.forEach(personaje -> {
                    usuario_PersonaService.save(new Usuario_Personaje(usuario, personaje));
                });
            }

            usuario.setUsuarioPersonajes((new HashSet<>(usuario_PersonaService.findAll())));
        };
    }
}
