package container.Sombras.Repositorio;

import container.Sombras.Entidad.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {
    Habilidad findByName(String name);
}
