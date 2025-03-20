package container.Sombras.config;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Habilidad;
import container.Sombras.Repositorio.ClaseRepository;
import container.Sombras.Repositorio.HabilidadRepository;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.HabilidadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultHabilityData {
    @Bean
    @Order(2)
    CommandLineRunner initHabilityData(HabilidadService habilidadService){
        return args -> {
            List<Habilidad> habilidadesPorDefecto = new ArrayList<>();
            habilidadesPorDefecto.add(new Habilidad("Acrobacias", ""));
            habilidadesPorDefecto.add(new Habilidad("Atletismo", ""));
            habilidadesPorDefecto.add(new Habilidad("Engaño", ""));
            habilidadesPorDefecto.add(new Habilidad("Interpretacion", ""));
            habilidadesPorDefecto.add(new Habilidad("Intimidacion", ""));
            habilidadesPorDefecto.add(new Habilidad("Juego de manos", ""));
            habilidadesPorDefecto.add(new Habilidad("Medicina", ""));
            habilidadesPorDefecto.add(new Habilidad("Percepcion", ""));
            habilidadesPorDefecto.add(new Habilidad("Perspicacia", ""));
            habilidadesPorDefecto.add(new Habilidad("Persuasion", ""));
            habilidadesPorDefecto.add(new Habilidad("Religion", ""));
            habilidadesPorDefecto.add(new Habilidad("Sigilo", ""));

            for(Habilidad habilidad : habilidadesPorDefecto){
                if (habilidadService.findByName(habilidad.getName())==null){
                    habilidadService.save(habilidad);
                };
            }
        };
    }
}
