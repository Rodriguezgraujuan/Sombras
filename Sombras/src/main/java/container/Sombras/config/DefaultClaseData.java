package container.Sombras.config;

import container.Sombras.Entidad.Clase;
import container.Sombras.Servicio.ClaseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
@Profile("!test")
public class DefaultClaseData {
    @Bean
    @Order(4)
    CommandLineRunner initClaseData(ClaseService claseService) {
        return args -> {
            List<Clase> clasesPorDefecto = new ArrayList<>();
            clasesPorDefecto.add(new Clase("Barbaro", "d12", null, new HashSet<>(),"Son guerreros poderosos que funcionan gracias a fuerzas primarias del multiverso que se manifiestan como Furia."));
            clasesPorDefecto.add(new Clase("Bardo", "d8", "Lanzador Puro", new HashSet<>(),"Invocando magia a través de música, danza y el verso. Son expertos en inspirar a otros, aliviar heridas y crear ilusiones."));
            clasesPorDefecto.add(new Clase("Brujo", "d8", "Lanzador de Pacto", new HashSet<>(),"En búsqueda del conocimiento que yace oculto en el tejido del multiverso, secretos arcanos para reforzar su propio poder."));
            clasesPorDefecto.add(new Clase("Clerigo", "d8", "Lanzador Puro", new HashSet<>(),"Pueden acceder a la magia divina de los planos exteriores y canalizarla para fortalecer a la gente y luchar contra los enemigos."));
            clasesPorDefecto.add(new Clase("Druida", "d8", "Lanzador Puro", new HashSet<>(),"Invoca las fuerzas de la naturaleza, aprovecha la magia para curar, transformarte en animales y ejercer destrucción elemental."));
            clasesPorDefecto.add(new Clase("Explorador", "d10", "Lanzador Medio", new HashSet<>(),"Saben rastrear a su presa como lo hace un depredador, moviéndose sigilosamente por la naturaleza y ocultándose entre la maleza y los escombros.  "));
            clasesPorDefecto.add(new Clase("Guerrero", "d10", null, new HashSet<>(),"Poseen una destreza sin igual con armas y armaduras. Y conocen bien la muerte, tanto al imponerla como al desafiarla."));
            clasesPorDefecto.add(new Clase("Hechicero", "d6", "Lanzador Puro", new HashSet<>(),"Aprovechan y canalizan el poder crudo y agitado de la magia innata que está impresa en su propio ser."));
            clasesPorDefecto.add(new Clase("Mago", "d6", "Lanzador Puro", new HashSet<>(),"Lanza hechizos de fuego explosivos, rayos en arco, engaños sutiles y transformaciones espectaculares."));
            clasesPorDefecto.add(new Clase("Monje", "d8", null, new HashSet<>(),"Concentran sus reservas internas de poder para crear efectos extraordinarios, incluso sobrenaturales."));
            clasesPorDefecto.add(new Clase("Paladin", "d10", "Lanzador Medio", new HashSet<>(),"El juramento del paladín es un vínculo poderoso, es una fuente de poder que convierte a un guerrero devoto en un campeón bendecido."));
            clasesPorDefecto.add(new Clase("Picaro", "d8", null, new HashSet<>(),"Tienen un don para encontrar la solución a casi cualquier problema. Algunos incluso aprenden trucos de magia para complementar sus otras habilidades."));

            for (Clase clase : clasesPorDefecto){
                if (claseService.findByNombre(clase.getNombre())==null){
                    claseService.save(clase);
                }
            }
        };
    }
}
