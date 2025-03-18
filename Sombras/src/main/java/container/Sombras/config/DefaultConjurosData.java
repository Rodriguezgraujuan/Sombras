package container.Sombras.config;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Entidad.Conjuros;
import container.Sombras.Repositorio.AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import container.Sombras.Servicio.ConjuroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultConjurosData {
    @Bean
    CommandLineRunner initMagicData(ConjuroService conjuroService){
        return args -> {
            List<Conjuros> conjurosPorDefecto = new ArrayList<>();

            for(Conjuros conjuro : conjurosPorDefecto){
                if (conjuroService.findByNombre(conjuro.getNombre())==null){
                    conjuroService.save(conjuro);
                };
            }
        };
    }
}
