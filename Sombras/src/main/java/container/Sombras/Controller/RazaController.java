package container.Sombras.Controller;

import container.Sombras.Entidad.Raza;
import container.Sombras.Entidad.Raza_Habilidad;
import container.Sombras.Repositorio.RazaHabilidadRepository;
import container.Sombras.Servicio.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RazaController {

    @Autowired
    RazaService razaService;
    @Autowired
    private RazaHabilidadRepository razaHabilidadRepository;

    @GetMapping("/razas")
    List<Raza> razas() {
        return razaService.findAll();
    }

    @GetMapping("/razaHabilidades")
    List<Raza_Habilidad> razaHabilidades(Long id){
        System.out.println(razaHabilidadRepository.findByRaza(razaService.findById(id)));
        return razaHabilidadRepository.findByRaza(razaService.findById(id));
    }
}
