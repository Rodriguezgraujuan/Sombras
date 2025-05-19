package container.Sombras.config;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Conjuros;
import container.Sombras.Repositorio.AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import container.Sombras.Servicio.ClaseService;
import container.Sombras.Servicio.ConjuroService;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Profile("!test")
public class DefaultConjurosData {
    @Bean
    @Order(6)
    CommandLineRunner initMagicData(ConjuroService conjuroService, ClaseService claseService){
        return args -> {
            List<Conjuros> conjurosPorDefecto = new ArrayList<>();
            // Bardo
            conjurosPorDefecto.add(new Conjuros("Burla Cruel", "Insulta mágicamente a un enemigo, causándole daño psicológico y desventaja.", claseService.findByNombre("Bardo")));
            conjurosPorDefecto.add(new Conjuros("Disfrazarse", "Cambia temporalmente tu apariencia.", claseService.findByNombre("Bardo")));
            conjurosPorDefecto.add(new Conjuros("Inspiración Heroica", "Otorga un bono a las tiradas de aliados cercanos.", claseService.findByNombre("Bardo")));
            conjurosPorDefecto.add(new Conjuros("Silencio Encantado", "Crea un área donde el sonido no puede existir.", claseService.findByNombre("Bardo")));
            conjurosPorDefecto.add(new Conjuros("Grito Desgarrador", "Un grito explosivo que aturde a los enemigos cercanos.", claseService.findByNombre("Bardo")));

            // Brujo
            conjurosPorDefecto.add(new Conjuros("Rayo de Agonía", "Un rayo de energía oscura que drena la vida del enemigo.", claseService.findByNombre("Brujo")));
            conjurosPorDefecto.add(new Conjuros("Pacto de Sangre", "Sacrifica puntos de vida para potenciar un hechizo.", claseService.findByNombre("Brujo")));
            conjurosPorDefecto.add(new Conjuros("Palabra del Abismo", "Un comando oscuro que obliga a un enemigo a obedecer.", claseService.findByNombre("Brujo")));
            conjurosPorDefecto.add(new Conjuros("Oscuridad Profunda", "Genera una sombra impenetrable.", claseService.findByNombre("Brujo")));
            conjurosPorDefecto.add(new Conjuros("Explosión Sobrenatural", "Un estallido de energía mágica que puede encadenarse entre enemigos.", claseService.findByNombre("Brujo")));

            // Clérigo
            conjurosPorDefecto.add(new Conjuros("Curar Heridas", "Restaura los puntos de vida de un aliado.", claseService.findByNombre("Clerigo")));
            conjurosPorDefecto.add(new Conjuros("Castigo Sagrado", "Un golpe radiante que inflige daño extra a criaturas malignas.", claseService.findByNombre("Clerigo")));
            conjurosPorDefecto.add(new Conjuros("Escudo de Fe", "Aumenta temporalmente la defensa de un aliado.", claseService.findByNombre("Clerigo")));
            conjurosPorDefecto.add(new Conjuros("Luz Sagrada", "Ilumina el área y debilita a los no-muertos.", claseService.findByNombre("Clerigo")));
            conjurosPorDefecto.add(new Conjuros("Expulsar No-Muertos", "Obliga a los muertos vivientes a huir.", claseService.findByNombre("Clerigo")));

            // Druida
            conjurosPorDefecto.add(new Conjuros("Forma Salvaje", "Te transformas en un animal por un tiempo.", claseService.findByNombre("Druida")));
            conjurosPorDefecto.add(new Conjuros("Enredaderas Espinosas", "Invoca raíces para atrapar a los enemigos.", claseService.findByNombre("Druida")));
            conjurosPorDefecto.add(new Conjuros("Niebla de la Naturaleza", "Genera una densa neblina que oculta la visión.", claseService.findByNombre("Druida")));
            conjurosPorDefecto.add(new Conjuros("Tormenta Elemental", "Lanza rayos y viento para dañar a enemigos.", claseService.findByNombre("Druida")));
            conjurosPorDefecto.add(new Conjuros("Fuerza del Oso", "Aumenta la resistencia y el poder físico de un aliado.", claseService.findByNombre("Druida")));

            // Explorador
            conjurosPorDefecto.add(new Conjuros("Sentido de la Naturaleza", "Detecta enemigos cercanos a través del entorno.", claseService.findByNombre("Explorador")));
            conjurosPorDefecto.add(new Conjuros("Vista de Halcón", "Aumenta la precisión en ataques a distancia.", claseService.findByNombre("Explorador")));
            conjurosPorDefecto.add(new Conjuros("Camuflaje Arcano", "Se oculta en la naturaleza sin dejar rastro.", claseService.findByNombre("Explorador")));
            conjurosPorDefecto.add(new Conjuros("Viento Ligero", "Aumenta la velocidad del usuario.", claseService.findByNombre("Explorador")));
            conjurosPorDefecto.add(new Conjuros("Flecha Arcana", "Un proyectil mágico que ignora defensas físicas.", claseService.findByNombre("Explorador")));

            // Hechicero
            conjurosPorDefecto.add(new Conjuros("Rayo Ígneo", "Lanza un rayo de fuego contra un enemigo.", claseService.findByNombre("Hechicero")));
            conjurosPorDefecto.add(new Conjuros("Explosión Arcana", "Una onda expansiva de energía mágica.", claseService.findByNombre("Hechicero")));
            conjurosPorDefecto.add(new Conjuros("Tormenta Caótica", "Un hechizo aleatorio de fuego, hielo o trueno.", claseService.findByNombre("Hechicero")));
            conjurosPorDefecto.add(new Conjuros("Escudo de Magia Pura", "Bloquea un ataque con energía mística.", claseService.findByNombre("Hechicero")));
            conjurosPorDefecto.add(new Conjuros("Erupción Mágica", "Una explosión de energía que deja el suelo en llamas.", claseService.findByNombre("Hechicero")));

            // Mago
            conjurosPorDefecto.add(new Conjuros("Misil Mágico", "Dispara proyectiles de energía infalibles.", claseService.findByNombre("Mago")));
            conjurosPorDefecto.add(new Conjuros("Rayo de Escarcha", "Ralentiza y congela al enemigo.", claseService.findByNombre("Mago")));
            conjurosPorDefecto.add(new Conjuros("Muralla de Fuerza", "Crea una barrera mágica invisible.", claseService.findByNombre("Mago")));
            conjurosPorDefecto.add(new Conjuros("Detener el Tiempo", "Congela el tiempo por unos instantes.", claseService.findByNombre("Mago")));
            conjurosPorDefecto.add(new Conjuros("Visión Verdadera", "Permite ver a través de ilusiones y engaños.", claseService.findByNombre("Mago")));

            // Paladín
            conjurosPorDefecto.add(new Conjuros("Golpe Divino", "Ataque bendecido con luz sagrada.", claseService.findByNombre("Paladin")));
            conjurosPorDefecto.add(new Conjuros("Juicio del Paladín", "Inflige más daño a enemigos malvados.", claseService.findByNombre("Paladin")));
            conjurosPorDefecto.add(new Conjuros("Aura de Protección", "Crea un campo defensivo para aliados cercanos.", claseService.findByNombre("Paladin")));
            conjurosPorDefecto.add(new Conjuros("Cadenas de Justicia", "Aprisiona a un enemigo con luz celestial.", claseService.findByNombre("Paladin")));
            conjurosPorDefecto.add(new Conjuros("Resplandor Sagrado", "Ciega a los enemigos con un destello divino.", claseService.findByNombre("Paladin")));

            //Ranger
            conjurosPorDefecto.add(new Conjuros("Agua Coral", "Aumenta la velocidad del usuario.", claseService.findByNombre("Ranger")));
            conjurosPorDefecto.add(new Conjuros("Golpe certero", "Ventaja en tu próximo ataque contra un enemigo, perfecto para asegurar un disparo certero.", claseService.findByNombre("Ranger")));
            conjurosPorDefecto.add(new Conjuros("Marca del cazador", "Aumenta el daño de tus ataques a distancia y te ayuda a rastrear a tu objetivo.", claseService.findByNombre("Ranger")));
            conjurosPorDefecto.add(new Conjuros("Disparo mágico", "Encanta un proyectil para atravesar resistencias o causar efectos adicionales, útil para enfrentarte a enemigos con defensas especiales.", claseService.findByNombre("Ranger")));
            conjurosPorDefecto.add(new Conjuros("Flecha de llamas", "Convierte tus flechas o virotes en proyectiles ardientes, añadiendo daño de fuego.", claseService.findByNombre("Ranger")));

            Set<Conjuros> conjuros = new HashSet<>();
            for (Conjuros conjuro : conjurosPorDefecto) {
                if (conjuroService.findByNombre(conjuro.getNombre()) == null) {
                    Clase clase = conjuro.getClase();
                    System.out.println("Nombre: "+ clase.getNombre());
                    if (clase != null) {
                        conjuro.setClase(clase);
                        conjuros.add(conjuro);
                        if (conjuros.size() >= 5) {
                            clase.setConjuros(conjuros);
                            conjuros = new HashSet<>();
                        }

                        conjuroService.save(conjuro);
                        claseService.save(clase);
                    }
                }
            }

        };
    }
}
