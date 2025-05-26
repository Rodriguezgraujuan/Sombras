package container.Sombras.personajeTests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Personaje;
import container.Sombras.Entidad.Raza;
import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import container.Sombras.Servicio.PersonajeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class PersonajeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonajeService personajeService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testCreateCharacter_WithValidRequest_ReturnsOk() throws Exception {
        String personajeJson = """
                {
                  "nombre": "John",
                  "apellido": "Doe",
                  "nivel": 1,
                  "descripcion": "Test",
                  "pulico": true,
                  "clase": 1,
                  "raza": 1
                }
                
                """;
        mockMvc.perform(post("/createCharacter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personajeJson))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testDeleteCharacter_WithValidRequest_ReturnsOk() throws Exception {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setEmail("testuser");
        mockUsuario.setId(1L);

        Personaje mockPersonaje = new Personaje();
        mockPersonaje.setId(10L);
        mockPersonaje.setUsuario(mockUsuario);

        Clase mockClase = new Clase();
        mockClase.setPersonajes(new HashSet<>());
        mockPersonaje.setClase(mockClase);
        mockClase.getPersonajes().add(mockPersonaje);

        Raza mockRaza = new Raza();
        mockRaza.setPersonaje(new HashSet<>());
        mockPersonaje.setRaza(mockRaza);
        mockRaza.getPersonaje().add(mockPersonaje);

        when(personajeService.findById(10L)).thenReturn(mockPersonaje);
        when(usuarioRepository.findByEmail("testuser")).thenReturn(mockUsuario);

        String requestJson = """
            {
              "id": 10
            }
            """;

        mockMvc.perform(post("/deleteCharacter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        verify(personajeService).delete(mockPersonaje);
    }

}
