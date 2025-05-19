package container.Sombras.personajeTests;
import container.Sombras.Controller.PersonajeController;
import container.Sombras.Dto.PersonajeDto;
import container.Sombras.Entidad.*;
import container.Sombras.Servicio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.*;

public class PersonajeControllerTest {
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private ClaseService claseService;
    @Mock
    private RazaService razaService;
    @Mock
    private PersonajeService personajeService;

    @InjectMocks
    private PersonajeController personajeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCharacter() {
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1L);

        Clase clase = new Clase();
        clase.setId(1L);
        clase.setNombre("Guerrero");

        Raza raza = new Raza();
        raza.setId(1L);
        raza.setName("Humano");

        PersonajeDto input = new PersonajeDto();
        input.setNombre("John");
        input.setApellido("Doe");
        input.setNivel(1);
        input.setDescripcion("Un guerrero valiente");
        input.setPulico(true);

        input.setClase(clase.getId());
        input.setRaza(raza.getId());

        when(claseService.findById(1L)).thenReturn(clase);
        when(razaService.findById(1L)).thenReturn(raza);
        when(usuarioService.findByEmail(anyString())).thenReturn(mockUsuario);

        mockStaticSecurityContext(mockUsuario);

        personajeController.createCharacter(input);

        verify(personajeService, times(1)).save(any(Personaje.class));
    }

    private void mockStaticSecurityContext(Usuario usuario) {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("usuario@email.com");

        org.springframework.security.core.context.SecurityContext context = mock(org.springframework.security.core.context.SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);

        org.springframework.security.core.context.SecurityContextHolder.setContext(context);

        when(usuarioService.findByEmail("usuario@email.com")).thenReturn(usuario);
    }


}
