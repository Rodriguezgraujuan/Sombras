package container.Sombras.config;

import container.Sombras.Entidad.Habilidad;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Servicio.HabilidadService;
import container.Sombras.Servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(5)
public class DefaultUserData {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUserData(UsuarioService usuarioService) {
        return args -> {
            List<Usuario> usuariosPorDefecto = List.of(
                    new Usuario("Admin", "admin@gmail.com", passwordEncoder.encode("admin"), "ADMIN"),
                    new Usuario("Usuario", "usuario@gmail.com", passwordEncoder.encode("user"), "USER")
            );

            for (Usuario usuario : usuariosPorDefecto) {
                if (usuarioService.findByEmail(usuario.getEmail()) == null) {
                    usuarioService.save(usuario);
                }
                ;
            }
        };
    }
}
