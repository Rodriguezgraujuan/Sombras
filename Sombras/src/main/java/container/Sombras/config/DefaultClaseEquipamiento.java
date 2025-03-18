package container.Sombras.config;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Clase_Equipamiento;
import container.Sombras.Entidad.Equipamiento;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.Clase_EquipamientoService;
import container.Sombras.Servicio.EquipamientoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DefaultClaseEquipamiento {

    @Bean
    CommandLineRunner initClaseEquipamiento(ClaseService claseService, EquipamientoService equipamientosService, Clase_EquipamientoService clase_equipamientoservice) {
        return args -> {
            Clase barbaro = claseService.findByNombre("Barbaro");
            Clase bardo = claseService.findByNombre("Bardo");
            Clase brujo = claseService.findByNombre("Brujo");
            Clase clerigo = claseService.findByNombre("Clerigo");
            Clase druida = claseService.findByNombre("Druida");
            Clase explorador = claseService.findByNombre("Explorador");
            Clase guerrero = claseService.findByNombre("Guerrero");
            Clase hechizero = claseService.findByNombre("Hechizero");
            Clase mago = claseService.findByNombre("Mago");
            Clase monje = claseService.findByNombre("Monje");
            Clase paladin = claseService.findByNombre("Paladin");
            Clase picaro = claseService.findByNombre("Picaro");

            List<Equipamiento> equipamientos = equipamientosService.findAll();

            equipamientos.forEach(equipamiento -> {
                switch (equipamiento.getName()){
                    case "Gran hacha (dos manos)":
                        clase_equipamientoservice.save(new Clase_Equipamiento());
                        break;
                    case "Dos hachas de guerra":
                        break;
                    case "Paquete de explorador":
                        break;
                    case "Cuatro jabalinas":
                        break;
                    case "Estoque":
                        break;
                    case "Espada larga":
                        break;
                    case "Paquete diplomatico":
                        break;
                    case "Paquete de artista":
                        break;
                    case "Laud":
                        break;
                    case "Trompeta":
                        break;
                    case "Flauta":
                        break;
                    case "Armadura de cuero":
                        break;
                    case "Daga":
                        break;
                    case "Ballesta ligera":
                        break;
                    case "Saco de artefactos":
                        break;
                    case "Canalizador Arcano":
                        break;
                    case "Dagas dobles":
                        break;
                    case "Maza":
                        break;
                    case "Martillo de guerra":
                        break;
                    case "Cota de escamas":
                        break;
                    case "Cota de malla":
                        break;
                    case "Escudo reforzado":
                        break;
                    case "Baston sagrado":
                        break;
                    case "Escudo de madera":
                        break;
                    case "Cimitarra":
                        break;
                    case "Dos espadas cortas":
                        break;
                    case "Arco largo":
                        break;
                    case "Baston enraizado":
                        break;
                    case "Libro de conjuros":
                        break;
                    case "Espada corta":
                        break;
                    case "10 Dardos":
                        break;
                    case "5 Flechas":
                        break;
                    case "10 Flechas":
                        break;
                    case "20 Flechas":
                        break;
                    case "Herramientas de ladron":
                        break;
                }
            });
        };
    }
}
