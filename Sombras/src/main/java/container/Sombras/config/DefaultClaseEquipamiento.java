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
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.*;

@Configuration
@Profile("!test")
public class DefaultClaseEquipamiento {

    private void saveIfNotExists(Clase_EquipamientoService service, Clase clase, Equipamiento equipamiento, Set<Clase_Equipamiento> claseSet ) {
        if (!service.existsByClaseAndEquipamiento(clase, equipamiento)) {
            System.out.println("Guardando nueva relación: " + clase.getNombre() + " - " + equipamiento.getName());
            Clase_Equipamiento nuevaRelacion = new Clase_Equipamiento(clase, equipamiento);
            service.save(nuevaRelacion);
            claseSet.add(nuevaRelacion);
        } else {
            System.out.println("Relación ya existe: " + clase.getNombre() + " - " + equipamiento.getName());
        }
    }



    @Bean
    @Order(8)
    CommandLineRunner initClaseEquipamiento(ClaseService claseService, EquipamientoService equipamientosService, Clase_EquipamientoService clase_equipamientoservice) {
        return args -> {
            clase_equipamientoservice.deleteAll();
            Clase barbaro = claseService.findByNombre("Barbaro");
            Clase bardo = claseService.findByNombre("Bardo");
            Clase brujo = claseService.findByNombre("Brujo");
            Clase clerigo = claseService.findByNombre("Clerigo");
            Clase druida = claseService.findByNombre("Druida");
            Clase explorador = claseService.findByNombre("Explorador");
            Clase guerrero = claseService.findByNombre("Guerrero");
            Clase hechicero = claseService.findByNombre("Hechicero");
            Clase mago = claseService.findByNombre("Mago");
            Clase monje = claseService.findByNombre("Monje");
            Clase paladin = claseService.findByNombre("Paladin");
            Clase picaro = claseService.findByNombre("Picaro");
            Clase caballero = claseService.findByNombre("Caballero");
            Clase orco = claseService.findByNombre("Orco");
            Clase ranger = claseService.findByNombre("Ranger");
            Set<Clase_Equipamiento> barbaoSet = new HashSet<>();
            Set<Clase_Equipamiento> bardoSet = new HashSet<>();
            Set<Clase_Equipamiento> brujoSet = new HashSet<>();
            Set<Clase_Equipamiento> clerigoSet = new HashSet<>();
            Set<Clase_Equipamiento> druidaSet = new HashSet<>();
            Set<Clase_Equipamiento> exploradorSet = new HashSet<>();
            Set<Clase_Equipamiento> guerreroSet = new HashSet<>();
            Set<Clase_Equipamiento> hechizeroSet = new HashSet<>();
            Set<Clase_Equipamiento> magoSet = new HashSet<>();
            Set<Clase_Equipamiento> monjeSet = new HashSet<>();
            Set<Clase_Equipamiento> paladinSet = new HashSet<>();
            Set<Clase_Equipamiento> picaroSet = new HashSet<>();
            Set<Clase_Equipamiento> caballeroSet = new HashSet<>();
            Set<Clase_Equipamiento> orcoSet = new HashSet<>();
            Set<Clase_Equipamiento> rangerSet = new HashSet<>();



            List<Equipamiento> equipamientos = equipamientosService.findAll();

            equipamientos.forEach(equipamiento -> {
                switch (equipamiento.getName()) {
                    case "Gran hacha (dos manos)":
                        saveIfNotExists(clase_equipamientoservice, orco, equipamiento, orcoSet);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento, guerreroSet);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento, barbaoSet);
                        break;
                    case "Dos hachas de guerra":
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento, guerreroSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento, bardoSet);
                        break;
                    case "Paquete de explorador":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento, rangerSet);
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento, caballeroSet);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento, picaroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento, monjeSet);
                        saveIfNotExists(clase_equipamientoservice, hechicero, equipamiento, hechizeroSet);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento, guerreroSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento, barbaoSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Cuatro jabalinas":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento, rangerSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento, monjeSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Estoque":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento, rangerSet);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento, picaroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, hechicero, equipamiento, hechizeroSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Espada larga":
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento, caballeroSet);
                        break;
                    case "Armadura reforzada":
                        saveIfNotExists(clase_equipamientoservice, orco, equipamiento, orcoSet);
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento, caballeroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        break;
                    case "Paquete diplomatico":
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento, caballeroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento, monjeSet);
                        saveIfNotExists(clase_equipamientoservice, mago, equipamiento, magoSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Paquete de artista":
                    case "Laud":
                    case "Trompeta":
                    case "Flauta":
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Armadura de cuero":
                        saveIfNotExists(clase_equipamientoservice, orco, equipamiento, orcoSet);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento, picaroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento, monjeSet);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento, guerreroSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento, barbaoSet);
                        saveIfNotExists(clase_equipamientoservice, brujo, equipamiento, brujoSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Daga":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento, rangerSet);
                        saveIfNotExists(clase_equipamientoservice, caballero, equipamiento, caballeroSet);
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento, picaroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento, monjeSet);
                        saveIfNotExists(clase_equipamientoservice, hechicero, equipamiento, hechizeroSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Ballesta ligera":
                    case "10 Flechas":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento, rangerSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        break;
                    case "Saco de artefactos":
                        saveIfNotExists(clase_equipamientoservice, mago, equipamiento, magoSet);
                        saveIfNotExists(clase_equipamientoservice, hechicero, equipamiento, hechizeroSet);
                        saveIfNotExists(clase_equipamientoservice, druida, equipamiento, druidaSet);
                        saveIfNotExists(clase_equipamientoservice, clerigo, equipamiento, clerigoSet);
                        saveIfNotExists(clase_equipamientoservice, brujo, equipamiento, brujoSet);
                        break;
                    case "Canalizador Arcano":
                        saveIfNotExists(clase_equipamientoservice, mago, equipamiento, magoSet);
                        saveIfNotExists(clase_equipamientoservice, hechicero, equipamiento, hechizeroSet);
                        saveIfNotExists(clase_equipamientoservice, brujo, equipamiento, brujoSet);
                        break;
                    case "Cimitarra":
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento, picaroSet);
                        saveIfNotExists(clase_equipamientoservice, monje, equipamiento, monjeSet);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento, guerreroSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        saveIfNotExists(clase_equipamientoservice, clerigo, equipamiento, clerigoSet);
                        saveIfNotExists(clase_equipamientoservice, barbaro, equipamiento, barbaoSet);
                        saveIfNotExists(clase_equipamientoservice, bardo, equipamiento, bardoSet);
                        break;
                    case "Dos espadas cortas":
                    case "Espada corta":
                        saveIfNotExists(clase_equipamientoservice, picaro, equipamiento, picaroSet);
                        saveIfNotExists(clase_equipamientoservice, paladin, equipamiento, paladinSet);
                        saveIfNotExists(clase_equipamientoservice, guerrero, equipamiento, guerreroSet);
                        saveIfNotExists(clase_equipamientoservice, explorador, equipamiento, exploradorSet);
                        break;
                    case "Arco largo":
                    case "20 Flechas":
                        saveIfNotExists(clase_equipamientoservice, ranger, equipamiento, rangerSet);
                        break;
                }
            });
            saveClass(barbaro, claseService, barbaoSet);
            saveClass(bardo, claseService, bardoSet);
            saveClass(brujo, claseService, brujoSet);
            saveClass(clerigo, claseService, clerigoSet);
            saveClass(druida, claseService, druidaSet);
            saveClass(explorador, claseService, exploradorSet);
            saveClass(guerrero, claseService, guerreroSet);
            saveClass(hechicero, claseService, hechizeroSet);
            saveClass(mago, claseService, magoSet);
            saveClass(monje, claseService, monjeSet);
            saveClass(paladin, claseService, paladinSet);
            saveClass(picaro, claseService, picaroSet);
            saveClass(caballero, claseService, caballeroSet);
            saveClass(orco, claseService, orcoSet);
            saveClass(ranger, claseService, rangerSet);
        };
    }

    public void saveClass(Clase clase, ClaseService claseService, Set<Clase_Equipamiento> claseSet) {
        clase.setClaseEquipamientos(claseSet);
        claseService.save(clase);
    }
}
