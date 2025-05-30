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
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("!test")
public class DefaultHabilityData {
    @Bean
    @Order(2)
    CommandLineRunner initHabilityData(HabilidadService habilidadService){
        return args -> {
            List<Habilidad> habilidadesPorDefecto = new ArrayList<>();
            habilidadesPorDefecto.add(new Habilidad("Acrobacias", "Refleja la destreza del personaje para realizar maniobras ágiles como saltos, volteretas, mantener el equilibrio o esquivar obstáculos."));
            habilidadesPorDefecto.add(new Habilidad("Atletismo", "Mide la capacidad física del personaje para nadar, trepar, correr, saltar y realizar proezas de fuerza o resistencia."));
            habilidadesPorDefecto.add(new Habilidad("Engaño", "Permite al personaje mentir de forma convincente, fingir emociones, ocultar intenciones o disfrazarse."));
            habilidadesPorDefecto.add(new Habilidad("Interpretacion", "Representa la capacidad del personaje para entretener mediante actuación, música, danza u otras formas artísticas."));
            habilidadesPorDefecto.add(new Habilidad("Intimidacion", "Evalúa la habilidad del personaje para coaccionar, amenazar o forzar la voluntad de otros mediante fuerza, presencia o palabras."));
            habilidadesPorDefecto.add(new Habilidad("Juego de manos", "Usada para trucos de prestidigitación, robar objetos sin ser notado o manipular cosas con gran precisión."));
            habilidadesPorDefecto.add(new Habilidad("Medicina", "Determina el conocimiento del personaje en primeros auxilios, tratamiento de heridas y estabilización de aliados caídos."));
            habilidadesPorDefecto.add(new Habilidad("Percepcion", "Representa la capacidad del personaje para notar detalles, detectar peligros, sonidos o movimientos en el entorno."));
            habilidadesPorDefecto.add(new Habilidad("Perspicacia", "Permite al personaje interpretar el lenguaje corporal, tono de voz y actitudes para detectar mentiras o intenciones ocultas."));
            habilidadesPorDefecto.add(new Habilidad("Persuasion", "Evalúa la habilidad del personaje para influir en otros de manera amistosa, negociar o ganarse la confianza."));
            habilidadesPorDefecto.add(new Habilidad("Religion", "Refleja el conocimiento del personaje sobre deidades, rituales sagrados, mitología y dogmas religiosos."));
            habilidadesPorDefecto.add(new Habilidad("Sigilo", "Mide la capacidad del personaje para moverse en silencio, esconderse y evitar ser detectado."));

            for(Habilidad habilidad : habilidadesPorDefecto){
                if (habilidadService.findByName(habilidad.getName())==null){
                    habilidadService.save(habilidad);
                };
            }
        };
    }
}
