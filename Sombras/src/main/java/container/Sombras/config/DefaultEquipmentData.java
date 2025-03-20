package container.Sombras.config;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Entidad.Equipamiento;
import container.Sombras.Repositorio.AtributoRepository;
import container.Sombras.Servicio.AtributoService;
import container.Sombras.Servicio.EquipamientoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultEquipmentData {

    @Bean
    @Order(3)
    CommandLineRunner initEquipmentData(EquipamientoService equipamientoService){
        return args -> {
            List<Equipamiento> defaultEquipment = new ArrayList<>();
            defaultEquipment.add(new Equipamiento("Gran hacha (dos manos)"));
            defaultEquipment.add(new Equipamiento("Dos hachas de guerra"));
            defaultEquipment.add(new Equipamiento("Paquete de explorador"));
            defaultEquipment.add(new Equipamiento("Cuatro jabalinas"));
            defaultEquipment.add(new Equipamiento("Estoque"));
            defaultEquipment.add(new Equipamiento("Espada larga"));
            defaultEquipment.add(new Equipamiento("Paquete diplomatico"));
            defaultEquipment.add(new Equipamiento("Paquete de artista"));
            defaultEquipment.add(new Equipamiento("Laud"));
            defaultEquipment.add(new Equipamiento("Trompeta"));
            defaultEquipment.add(new Equipamiento("Flauta"));
            defaultEquipment.add(new Equipamiento("Armadura de cuero"));
            defaultEquipment.add(new Equipamiento("Daga"));
            defaultEquipment.add(new Equipamiento("Ballesta ligera"));
            defaultEquipment.add(new Equipamiento("Saco de artefactos"));
            defaultEquipment.add(new Equipamiento("Canalizador Arcano"));
            defaultEquipment.add(new Equipamiento("Dagas dobles"));
            defaultEquipment.add(new Equipamiento("Maza"));
            defaultEquipment.add(new Equipamiento("Martillo de guerra"));
            defaultEquipment.add(new Equipamiento("Cota de escamas"));
            defaultEquipment.add(new Equipamiento("Cota de malla"));
            defaultEquipment.add(new Equipamiento("Escudo reforzado"));
            defaultEquipment.add(new Equipamiento("Baston sagrado"));
            defaultEquipment.add(new Equipamiento("Escudo de madera"));
            defaultEquipment.add(new Equipamiento("Cimitarra"));
            defaultEquipment.add(new Equipamiento("Dos espadas cortas"));
            defaultEquipment.add(new Equipamiento("Arco largo"));
            defaultEquipment.add(new Equipamiento("Baston enraizado"));
            defaultEquipment.add(new Equipamiento("Libro de conjuros"));
            defaultEquipment.add(new Equipamiento("Espada corta"));
            defaultEquipment.add(new Equipamiento("10 Dardos"));
            defaultEquipment.add(new Equipamiento("5 Flechas"));
            defaultEquipment.add(new Equipamiento("10 Flechas"));
            defaultEquipment.add(new Equipamiento("20 Flechas"));
            defaultEquipment.add(new Equipamiento("Herramientas de ladron"));
            defaultEquipment.add(new Equipamiento("Paquete de erudito"));
            defaultEquipment.add(new Equipamiento("Paquete de explorador de mazmorras"));
            defaultEquipment.add(new Equipamiento("Paquete de sacerdote"));
            defaultEquipment.add(new Equipamiento("Paquete religioso"));
            defaultEquipment.add(new Equipamiento("Vara"));
            defaultEquipment.add(new Equipamiento("Baston de madera"));
            defaultEquipment.add(new Equipamiento("Armadura reforzada"));



            for(Equipamiento equipamiento : defaultEquipment){
                if (equipamientoService.findByName(equipamiento.getName())==null){
                    equipamientoService.save(equipamiento);
                };
            }
        };
    }
}
