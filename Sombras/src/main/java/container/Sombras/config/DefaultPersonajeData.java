package container.Sombras.config;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Personaje;
import container.Sombras.Entidad.Raza;
import container.Sombras.Repositorio.AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.PersonajeService;
import container.Sombras.Servicio.RazaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DefaultPersonajeData {

    @Bean
    @Order(9)
    CommandLineRunner initUserPlayersData(PersonajeService personajeService, ClaseService claseService, RazaService razaService){
        return args -> {
            List<Personaje> personajesPorDefecto = new ArrayList<>();
            personajesPorDefecto.add(new Personaje(true,"Alyndra","Moonshadow", claseService.findByNombre("Explorador"), razaService.findByName("Elfo"), "Una arquera sigilosa que conoce cada rincón del bosque. Siempre actúa con precisión y calma.","images/",1, 2,1,1,0,1));
            personajesPorDefecto.add(new Personaje(true,"Dorian","Ironfist", claseService.findByNombre("Guerrero"), razaService.findByName("Enano"), "Un veterano de muchas batallas, con una armadura pesada y un martillo capaz de aplastar cualquier escudo", "images/",1,0,2,0,1,0));
            personajesPorDefecto.add(new Personaje(true,"Seraphina","Valcrest", claseService.findByNombre("Hechicero"), razaService.findByName("Gnomo"), "Maestra de las artes arcanas, con un linaje misterioso que le otorga poderes sobrenaturales.", "images/",1,1,1,0,0,0));
            personajesPorDefecto.add(new Personaje(true,"Kael","Stormbringer", claseService.findByNombre("Paladin"), razaService.findByName("Humano"), "Un guerrero divino con un fuerte sentido de la justicia, bendecido por los dioses para erradicar el mal.", "images/",1,1,1,1,1,1));
            personajesPorDefecto.add(new Personaje(true,"Vex","Darkwhisper", claseService.findByNombre("Picaro"), razaService.findByName("Semielfo"), "Una sombra en la noche, especialista en eliminar enemigos antes de que se den cuenta de su presencia.", "images/",1,2,0,0,1,0));
            personajesPorDefecto.add(new Personaje(true,"Ragnar","Blacktide", claseService.findByNombre("Bardo"), razaService.findByName("Elfo"), "Encantador y astuto, usa su música tanto para inspirar aliados como para embaucar enemigos", "images/",1,2,1,1,0,1));
            personajesPorDefecto.add(new Personaje(true,"Drakar","Bloodscale", claseService.findByNombre("Barbaro"), razaService.findByName("Semiorco"), "Un guerrero brutal con la sangre de dragón corriendo por sus venas, imparable en la batalla", "images/",1,0,1,0,2,0));

            for(Personaje personaje : personajesPorDefecto){
                if (personajeService.findByNombre(personaje.getNombre())==null){
                    personajeService.save(personaje);
                };
            }
        };
    }
}
