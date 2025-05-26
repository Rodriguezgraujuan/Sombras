package container.Sombras.UsuarioControllerTest;

import container.Sombras.Controller.UsuarioController;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import container.Sombras.Servicio.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void createUsuario_conDatosValidos_retornaCreated() {
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("Password123");

        when(usuarioService.findByEmail("test@example.com")).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn("hashedPass");

        ResponseEntity<?> response = usuarioController.create(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuario creado con éxito", response.getBody());
    }

    @Test
    void createUsuario_conCorreoYaRegistrado_retornaBadRequest() {
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("Password123");

        when(usuarioService.findByEmail("test@example.com")).thenReturn(new Usuario());

        ResponseEntity<?> response = usuarioController.create(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El correo ya esta registrado", response.getBody());
    }

    @Test
    void createUsuario_conPasswordInvalida_retornaBadRequest() {
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("123"); // Muy corta

        ResponseEntity<?> response = usuarioController.create(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(((String) response.getBody()).contains("Longitud de contraseña"));
    }

}
