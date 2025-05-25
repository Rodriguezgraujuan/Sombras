package container.Sombras.config;

import container.Sombras.Entidad.*;
import container.Sombras.Repositorio.RazaHabilidadRepository;
import container.Sombras.Repositorio.RazaRepository;
import container.Sombras.Repositorio.Raza_AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.HabilidadService;
import container.Sombras.Servicio.RazaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.*;

@Configuration
@Profile("!test")
public class DefaultRazaData {

    @Bean
    @Order(7)
    CommandLineRunner initRazaData(RazaService razaService, ClaseService claseService, AtributoService atributoService, Raza_AtributoRepository raza_AtributoRepository, RazaRepository razaRepository, HabilidadService habilidadService, RazaHabilidadRepository razaHabilidadRepository) {
        return args -> {
            List<Raza> razasPorDefecto = new ArrayList<>();
            razasPorDefecto.add(new Raza("Humano", 1.75, 25, "Versátiles y ambiciosos, los humanos se adaptan con facilidad a cualquier entorno. Su tenacidad y diversidad cultural les permite destacar en múltiples disciplinas."));
            razasPorDefecto.add(new Raza("Elfo", 1.95, 30, "Seres longevos y esbeltos con una profunda conexión con la naturaleza y la magia. Conocidos por su aguda percepción, elegancia y habilidades arcanas."));
            razasPorDefecto.add(new Raza("Semielfo", 1.80, 25, "Híbridos de humanos y elfos, heredan lo mejor de ambos mundos: la adaptabilidad humana y la sensibilidad élfica. A menudo se sienten divididos entre dos culturas."));
            razasPorDefecto.add(new Raza("Gnomo", 0.6, 15, "Pequeños, curiosos e ingeniosos, los gnomos son conocidos por su amor por la invención, la alquimia y la magia ilusoria. Su espíritu alegre los hace muy sociables."));
            razasPorDefecto.add(new Raza("Semiorco", 2.8, 25, "De fuerza imponente y aspecto temible, los semiorcos son resistentes guerreros. Aunque muchas veces juzgados por su herencia orca, algunos buscan redimirse o encontrar su lugar en el mundo."));
            razasPorDefecto.add(new Raza("Enano", 0.5, 15, "Robustos, obstinados y valientes, los enanos son maestros artesanos y guerreros formidables. Valoran la tradición, el honor y la lealtad por encima de todo."));

            for (Raza raza : razasPorDefecto){
                if (razaService.findByName(raza.getName())==null){
                    System.out.println("Raza Save 1: "+raza.getName());
                    razaService.save(raza);
                }
            }

            List<Raza_Atributo> raza_atributosPorDefecto = new ArrayList<>();
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Humano"), atributoService.findByName("Todas")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Humano"), atributoService.findByName("Cualquiera")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semielfo"), atributoService.findByName("Destreza Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semielfo"), atributoService.findByName("Cualquiera")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Destreza Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Inteligencia")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Sabiduria")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Elfo"), atributoService.findByName("Constitucion")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Gnomo"), atributoService.findByName("Inteligencia Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Gnomo"), atributoService.findByName("Destreza")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Gnomo"), atributoService.findByName("Constitucion")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semiorco"), atributoService.findByName("Fuerza Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Semiorco"), atributoService.findByName("Constitucion")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Enano"), atributoService.findByName("Constitucion Experto")));
            raza_atributosPorDefecto.add(new Raza_Atributo(razaService.findByName("Enano"), atributoService.findByName("Fuerza")));
            raza_atributosPorDefecto.forEach(raza_atributo -> {
                if (!raza_AtributoRepository.existsByRazaAndAtributo(raza_atributo.getRaza(), raza_atributo.getAtributo())){
                    System.out.println("Raza: "+raza_atributo.getRaza().getName() + " Atributo: " + raza_atributo.getAtributo().getName());
                    raza_AtributoRepository.save(raza_atributo);
                }
            });
            razasPorDefecto.forEach(raza -> {
                Set<Raza_Atributo> raza_atributos = new HashSet<>();

                raza_atributosPorDefecto.forEach(raza_atributo -> {
                    if (raza_atributo.getRaza().getName().equals(raza.getName())) {
                        raza_atributos.add(raza_atributo);
                    }
                });

                if (!raza_atributos.isEmpty() && raza.getAtributoRaza().size() != raza_atributos.size()) {
                    Raza existingRaza = razaRepository.findByName(raza.getName());

                    if (existingRaza!=null) {
                        existingRaza.setAtributoRaza(raza_atributos);

                        razaRepository.save(existingRaza);
                    }
                }
            });


            List<Raza_Habilidad> raza_habilidadPorDefecto = new ArrayList<>();
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Humano"), habilidadService.findByName("Acrobacias")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Humano"), habilidadService.findByName("Atletismo")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Humano"), habilidadService.findByName("Juego de manos")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Semielfo"), habilidadService.findByName("Perspicacia")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Semielfo"), habilidadService.findByName("Interpretacion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Semielfo"), habilidadService.findByName("Sigilo")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Elfo"), habilidadService.findByName("Atletismo")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Elfo"), habilidadService.findByName("Medicina")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Elfo"), habilidadService.findByName("Religion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Gnomo"), habilidadService.findByName("Sigilo")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Gnomo"), habilidadService.findByName("Percepcion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Gnomo"), habilidadService.findByName("Religion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Semiorco"), habilidadService.findByName("Intimidacion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Semiorco"), habilidadService.findByName("Percepcion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Enano"), habilidadService.findByName("Medicina")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Enano"), habilidadService.findByName("Persuasion")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Enano"), habilidadService.findByName("Engaño")));
            raza_habilidadPorDefecto.add(new Raza_Habilidad(razaService.findByName("Enano"), habilidadService.findByName("Juego de manos")));

            raza_habilidadPorDefecto.forEach(raza_habilidad -> {
                if (!razaHabilidadRepository.existsByRazaAndHabilidad(raza_habilidad.getRaza(), raza_habilidad.getHabilidad())){
                    System.out.println("Raza: "+raza_habilidad.getRaza().getName() + " Habilidad: " + raza_habilidad.getHabilidad().getName());
                    razaHabilidadRepository.save(raza_habilidad);
                }
            });

            razasPorDefecto.forEach(raza -> {
                Set<Raza_Habilidad> razaHabilidades = new HashSet<>();

                raza_habilidadPorDefecto.forEach(raza_habilidad -> {
                    if (raza_habilidad.getHabilidad().getName().equals(raza.getName())) {
                        razaHabilidades.add(raza_habilidad);
                    }
                });

                if (!razaHabilidades.isEmpty() && raza.getAtributoRaza().size() != razaHabilidades.size()) {
                    Raza existingRaza = razaRepository.findByName(raza.getName());

                    if (existingRaza!=null) {
                        existingRaza.setHability(razaHabilidades);

                        razaRepository.save(existingRaza);
                    }
                }
            });
        };
    }
}
