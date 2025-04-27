package container.Sombras.Controller;

import container.Sombras.Entidad.Clase;
import container.Sombras.Servicio.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClaseController {

    @Autowired
    ClaseService claseService;

    @GetMapping("/clases")
    List<Clase> clases() {
        return claseService.findAll();
    }
}
