package container.Sombras.Controller;

import container.Sombras.Entidad.Raza;
import container.Sombras.Servicio.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RazaController {

    @Autowired
    RazaService razaService;

    @GetMapping("/razas")
    List<Raza> razas() {
        return razaService.findAll();
    }
}
