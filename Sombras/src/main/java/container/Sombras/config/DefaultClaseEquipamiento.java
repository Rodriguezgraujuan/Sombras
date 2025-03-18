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

    private void saveIfNotExists(Clase_EquipamientoService service, Clase clase, Equipamiento equipamiento) {
        if (!service.existsByClaseAndEquipamiento(clase, equipamiento)) {
            service.save(new Clase_Equipamiento(clase, equipamiento));
        }
    }

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
            Clase hechizero = claseService.findByNombre("Hechicero");
            Clase mago = claseService.findByNombre("Mago");
            Clase monje = claseService.findByNombre("Monje");
            Clase paladin = claseService.findByNombre("Paladin");
            Clase picaro = claseService.findByNombre("Picaro");
            Clase caballero = claseService.findByNombre("Caballero");
            Clase orco = claseService.findByNombre("Orco");
            Clase ranger = claseService.findByNombre("Ranger");
            System.out.println("Clases cargadas");
            System.out.println(ranger);

            List<Equipamiento> equipamientos = equipamientosService.findAll();

            equipamientos.forEach(equipamiento -> {
                switch (equipamiento.getName()) {
                    case "Gran hacha (dos manos)":
                        saveIfNotExists(clase_equipamientoservice, orco, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento);
                        break;
                    case "Dos hachas de guerra":
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento);
                        break;
                    case "Paquete de explorador":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, hechizero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Cuatro jabalinas":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Estoque":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, hechizero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Espada larga":
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento);
                        break;
                    case "Armadura reforzada":
                        saveIfNotExists(clase_equipamientoservice, orco, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        break;
                    case "Paquete diplomatico":
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, mago, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Paquete de artista":
                    case "Laud":
                    case "Trompeta":
                    case "Flauta":
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Armadura de cuero":
                        saveIfNotExists(clase_equipamientoservice, orco, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, brujo, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Daga":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, hechizero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Ballesta ligera":
                    case "10 Flechas":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        break;
                    case "Saco de artefactos":
                        saveIfNotExists(clase_equipamientoservice, mago, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, hechizero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, druida, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, clerigo, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, brujo, equipamiento);
                        break;
                    case "Canalizador Arcano":
                        saveIfNotExists(clase_equipamientoservice, mago, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, hechizero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, brujo, equipamiento);
                        break;
                    case "Cimitarra":
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, clerigo, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento);
                        break;
                    case "Dos espadas cortas":
                    case "Espada corta":
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento);
                        break;
                    case "Arco largo":
                    case "20 Flechas":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento);
                        break;
                }
            });
        };
    }
}
