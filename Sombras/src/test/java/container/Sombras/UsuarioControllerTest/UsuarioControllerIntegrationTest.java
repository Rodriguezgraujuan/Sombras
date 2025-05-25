package container.Sombras.UsuarioControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanBefore() {
        Usuario usuario = usuarioRepository.findByEmail("test@example.com");
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }
    @AfterEach
    void cleanAfter() {
        usuarioRepository.findByEmail("test@example.com");
    }

    @Test
    void registerUsuario_conDatosValidos_retornaCreated() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("Password123");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Usuario creado con éxito"));
    }

    @Test
    void registerUsuario_conFaltaDeDatos_retornaBadRequest() throws Exception {
        Usuario usuario = new Usuario();

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"USER"})
    void userInfo_retornaUsuarioAutenticado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("Password123");
        usuarioRepository.save(usuario);

        mockMvc.perform(get("/userInfo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

}
