package container.Sombras.Repositorio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Conjuros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConjuroReposiroty extends JpaRepository<Conjuros, Long> {
    List<Conjuros> findByClase(Clase clase);
    Conjuros findByNombre(String nombre);
}
