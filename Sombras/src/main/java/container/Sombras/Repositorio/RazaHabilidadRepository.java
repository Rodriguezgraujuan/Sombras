package container.Sombras.Repositorio;

import container.Sombras.Entidad.Habilidad;
import container.Sombras.Entidad.Raza;
import container.Sombras.Entidad.Raza_Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RazaHabilidadRepository extends JpaRepository<Raza_Habilidad, Long> {
    boolean existsByRazaAndHabilidad(Raza raza, Habilidad habilidad);
    List<Raza_Habilidad> findByRaza(Raza raza);
}
