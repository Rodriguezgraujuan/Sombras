package container.Sombras.Repositorio;

import container.Sombras.Entidad.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    Clase findByClase(String clase);
}
