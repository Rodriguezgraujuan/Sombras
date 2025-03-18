package container.Sombras.config;

import container.Sombras.Entidad.Clase;
import container.Sombras.Servicio.ClaseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
public class DefaultClaseData {
    @Bean
    CommandLineRunner initClaseData(ClaseService claseService) {
        return args -> {
            List<Clase> clasesPorDefecto = new ArrayList<>();
            clasesPorDefecto.add(new Clase("Barbaro", "d12", null, new HashSet<>()));
            clasesPorDefecto.add(new Clase("Bardo", "d8", "Lanzador Puro", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Brujo", "d8", "Lanzador de Pacto", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Clerigo", "d8", "Lanzador Puro", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Druida", "d8", "Lanzador Puro", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Explorador", "d10", "Lanzador Medio", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Guerrero", "d10", null, new HashSet<>()));
            clasesPorDefecto.add(new Clase("Hechicero", "d6", "Lanzador Puro", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Mago", "d6", "Lanzador Puro", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Monje", "d8", null, new HashSet<>()));
            clasesPorDefecto.add(new Clase("Paladin", "d10", "Lanzador Medio", new HashSet<>()));
            clasesPorDefecto.add(new Clase("Picaro", "d8", null, new HashSet<>()));
            clasesPorDefecto.add(new Clase("Caballero", "d10", null, new HashSet<>()));
            clasesPorDefecto.add(new Clase("Orco", "d14", null, new HashSet<>()));
            clasesPorDefecto.add(new Clase("Ranger", "d14", "Larnzador Largo", new HashSet<>()));

            for (Clase clase : clasesPorDefecto){
                if (claseService.findByNombre(clase.getNombre())==null){
                    claseService.save(clase);
                }
            }
        };
    }
}
