package container.Sombras.config;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Repositorio.AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultAtributeData {
    @Bean
    @Order(1)
    CommandLineRunner initAtributeData(AtributoRepository atributoRepository, AtributoService atributoService){
        return args -> {
            List<Atributo> atributosPorDefectos = new ArrayList<>();
            atributosPorDefectos.add(new Atributo("Todas","Todas las caracteristicas +1 punto",1));
            atributosPorDefectos.add(new Atributo("Cualquiera","+1 punto a cualquier atributo que quieras.",1));
            atributosPorDefectos.add(new Atributo("Inteligencia","Inteligencia +1 puntos",1));
            atributosPorDefectos.add(new Atributo("Destreza Experto","Destreza +2 puntos",2));
            atributosPorDefectos.add(new Atributo("Sabiduria","Sabiduria +1 puntos",1));
            atributosPorDefectos.add(new Atributo("Constitucion", "Constitucion +1 puntos",1));
            atributosPorDefectos.add(new Atributo("Inteligencia Experto","Inteligencia +2 puntos",2));
            atributosPorDefectos.add(new Atributo("Destreza","Destreza +1 puntos",1));
            atributosPorDefectos.add(new Atributo("Fuerza Experto","Fuerza +2 puntos",2));
            atributosPorDefectos.add(new Atributo("Constitucion Experto","Constitucion +2 puntos",2));
            atributosPorDefectos.add(new Atributo("Fuerza","Fuerza +1 puntos",1));

            for(Atributo atributo : atributosPorDefectos){
                if (atributoService.findByName(atributo.getName())==null){
                    atributoService.save(atributo);
                };
            }
        };
    }
}
