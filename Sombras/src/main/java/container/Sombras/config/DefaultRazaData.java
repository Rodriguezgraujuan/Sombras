package container.Sombras.config;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Raza;
import container.Sombras.Entidad.Raza_Atributo;
import container.Sombras.Repositorio.RazaRepository;
import container.Sombras.Repositorio.Raza_AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.RazaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DefaultRazaData {

    @Bean
    CommandLineRunner initRazaData(RazaService razaService, ClaseService claseService, AtributoService atributoService, Raza_AtributoRepository raza_AtributoRepository, RazaRepository razaRepository) {
        return args -> {
            List<Raza> razasPorDefecto = new ArrayList<>();
            razasPorDefecto.add(new Raza("Humano", 1.75, 25));
            razasPorDefecto.add(new Raza("Elfo", 1.95, 30));
            razasPorDefecto.add(new Raza("Semielfo", 1.80, 25));
            razasPorDefecto.add(new Raza("Gnomo", 0.6, 15));
            razasPorDefecto.add(new Raza("Semiorco", 2.8, 25));
            razasPorDefecto.add(new Raza("Enano", 0.5, 15));

            for (Raza raza : razasPorDefecto){
                if (razaService.findByName(raza.getName())==null){
                    razaService.save(raza);
                }
            }

            List<Raza_Atributo> raza_atributosPorDefecto = new ArrayList<>();
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Humano"), atributoService.findByName("Todas")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Humano"), atributoService.findByName("Cualquiera")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semielfo"), atributoService.findByName("Destreza Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semielfo"), atributoService.findByName("Cualquiera")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Destreza Experta")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Inteligencia")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Sabiduria")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Constitucion")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Gnomo"), atributoService.findByName("Inteligencia Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Gnomo"), atributoService.findByName("Destreza")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Gnomo"), atributoService.findByName("Constitucion")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semiorco"), atributoService.findByName("Fuerza Experta")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semiorco"), atributoService.findByName("Constitucion")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Enano"), atributoService.findByName("Constitucion Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Enano"), atributoService.findByName("Fuerza")));
            raza_atributosPorDefecto.forEach(raza_atributo -> {
                if (!raza_AtributoRepository.existsByRazaAndAtributo(raza_atributo.getRaza(), raza_atributo.getAtributo())){
                    raza_AtributoRepository.save(raza_atributo);
                }
            });
        };
    }
}
