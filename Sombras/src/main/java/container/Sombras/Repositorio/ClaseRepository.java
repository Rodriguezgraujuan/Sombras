package container.Sombras.Repositorio;

import container.Sombras.Entidad.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    Clase findByNombre(String clase);
}
