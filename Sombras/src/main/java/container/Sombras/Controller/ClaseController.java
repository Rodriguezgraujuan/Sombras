package container.Sombras.Controller;

import container.Sombras.Entidad.*;
import container.Sombras.Repositorio.Clase_EquipamientoRepository;
import container.Sombras.Repositorio.ConjuroReposiroty;
import container.Sombras.Repositorio.EquipamientoRepository;
import container.Sombras.Servicio.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClaseController {

    @Autowired
    ClaseService claseService;
    @Autowired
    private ConjuroReposiroty conjuroReposiroty;
    @Autowired
    private EquipamientoRepository equipamientoRepository;
    @Autowired
    private Clase_EquipamientoRepository clase_EquipamientoRepository;

    @GetMapping("/clases")
    List<Clase> clases() {
        return claseService.findAll();
    }

    @GetMapping("/claseConjuros")
    List<Conjuros> claseConjuros(Long id){
        System.out.println(conjuroReposiroty.findByClase(claseService.findById(id)));
        return conjuroReposiroty.findByClase(claseService.findById(id));
    }

    @GetMapping("/claseEquipamientos")
    List<Equipamiento> claseEquipamientos(Long id){
        List<Clase_Equipamiento> claseEquipamientos= clase_EquipamientoRepository.findByClase(claseService.findById(id));
        return claseEquipamientos.stream()
                .map(Clase_Equipamiento::getEquipamiento)
                .collect(Collectors.toList());
    }


}
