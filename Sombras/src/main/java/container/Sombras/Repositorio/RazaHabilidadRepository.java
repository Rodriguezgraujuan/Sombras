package container.Sombras.Repositorio;

import container.Sombras.Entidad.Raza_Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaHabilidadRepository extends JpaRepository<Raza_Habilidad, Long> {
    Raza_Habilidad findByName(String name);
}
