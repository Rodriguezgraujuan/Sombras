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
                        clase_equipamientoservice.save(new Clase_Equipamiento(barbaro, equipamiento));
                        break;
                    case "Dos hachas de guerra":
                        clase_equipamientoservice.save(new Clase_Equipamiento(barbaro, equipamiento));
                        break;
                    case "Paquete de explorador":
                        clase_equipamientoservice.save(new Clase_Equipamiento(barbaro, equipamiento));
                        break;
                    case "Cuatro jabalinas":
                        clase_equipamientoservice.save(new Clase_Equipamiento(barbaro, equipamiento));
                        break;
                    case "Estoque":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Espada larga":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Paquete diplomatico":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Paquete de artista":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Laud":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Trompeta":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Flauta":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Armadura de cuero":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Daga":
                        clase_equipamientoservice.save(new Clase_Equipamiento(bardo, equipamiento));
                        break;
                    case "Ballesta ligera":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        break;
                    case "Saco de artefactos":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        break;
                    case "Canalizador Arcano":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        break;
                    case "Dagas dobles":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        break;
                    case "Maza":
                        clase_equipamientoservice.save(new Clase_Equipamiento(clerigo, equipamiento));
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
                    case "Paquete de erudito":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        break;
                    case "Paquete de explorador de mazmorras":
                        clase_equipamientoservice.save(new Clase_Equipamiento(brujo, equipamiento));
                        break;
                    case "Paquete de sacerdote":
                        break;
                }
            });
        };
    }
}
