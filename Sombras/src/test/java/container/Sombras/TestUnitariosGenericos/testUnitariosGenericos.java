package container.Sombras.TestUnitariosGenericos;

import container.Sombras.Controller.ClaseController;
import container.Sombras.Controller.RazaController;
import container.Sombras.Entidad.*;
import container.Sombras.Repositorio.Clase_EquipamientoRepository;
import container.Sombras.Repositorio.ConjuroReposiroty;
import container.Sombras.Repositorio.EquipamientoRepository;
import container.Sombras.Repositorio.RazaHabilidadRepository;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.RazaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class testUnitariosGenericos {

    @InjectMocks
    private ClaseController claseController;

    @Mock
    private ClaseService claseService;

    @Mock
    private ConjuroReposiroty conjuroReposiroty;

    @Mock
    private EquipamientoRepository equipamientoRepository;

    @Mock
    private Clase_EquipamientoRepository clase_EquipamientoRepository;

    @InjectMocks
    private RazaController razaController;

    @Mock
    private RazaService razaService;

    @Mock
    private RazaHabilidadRepository razaHabilidadRepository;

    @Test
    void razas_retornaListaDeRazas() {
        Raza raza1 = new Raza();
        Raza raza2 = new Raza();
        List<Raza> razasEsperadas = Arrays.asList(raza1, raza2);

        when(razaService.findAll()).thenReturn(razasEsperadas);

        List<Raza> resultado = razaController.razas();

        assertEquals(razasEsperadas, resultado);
    }

    @Test
    void razaHabilidades_conIdValido_retornaListaDeHabilidades() {
        Long razaId = 1L;
        Raza raza = new Raza();
        Raza_Habilidad habilidad1 = new Raza_Habilidad();
        Raza_Habilidad habilidad2 = new Raza_Habilidad();
        List<Raza_Habilidad> habilidadesEsperadas = Arrays.asList(habilidad1, habilidad2);

        when(razaService.findById(razaId)).thenReturn(raza);
        when(razaHabilidadRepository.findByRaza(raza)).thenReturn(habilidadesEsperadas);

        List<Raza_Habilidad> resultado = razaController.razaHabilidades(razaId);

        assertEquals(habilidadesEsperadas, resultado);
    }

    @Test
    void clases_devuelveListaDeClases() {
        Clase clase1 = new Clase();
        Clase clase2 = new Clase();
        List<Clase> clasesEsperadas = Arrays.asList(clase1, clase2);

        when(claseService.findAll()).thenReturn(clasesEsperadas);

        List<Clase> resultado = claseController.clases();

        assertEquals(clasesEsperadas, resultado);
    }

    @Test
    void claseConjuros_devuelveListaDeConjuros() {
        Long claseId = 1L;
        Clase clase = new Clase();
        Conjuros conjuro1 = new Conjuros();
        Conjuros conjuro2 = new Conjuros();
        List<Conjuros> conjurosEsperados = Arrays.asList(conjuro1, conjuro2);

        when(claseService.findById(claseId)).thenReturn(clase);
        when(conjuroReposiroty.findByClase(clase)).thenReturn(conjurosEsperados);

        List<Conjuros> resultado = claseController.claseConjuros(claseId);

        assertEquals(conjurosEsperados, resultado);
    }

    @Test
    void claseEquipamientos_devuelveListaDeEquipamientos() {
        Long claseId = 1L;
        Clase clase = new Clase();
        Equipamiento equipamiento1 = new Equipamiento();
        Equipamiento equipamiento2 = new Equipamiento();

        Clase_Equipamiento ce1 = new Clase_Equipamiento();
        ce1.setEquipamiento(equipamiento1);
        Clase_Equipamiento ce2 = new Clase_Equipamiento();
        ce2.setEquipamiento(equipamiento2);

        List<Clase_Equipamiento> claseEquipamientos = Arrays.asList(ce1, ce2);

        when(claseService.findById(claseId)).thenReturn(clase);
        when(clase_EquipamientoRepository.findByClase(clase)).thenReturn(claseEquipamientos);

        List<Equipamiento> resultado = claseController.claseEquipamientos(claseId);

        assertEquals(Arrays.asList(equipamiento1, equipamiento2), resultado);
    }
}
