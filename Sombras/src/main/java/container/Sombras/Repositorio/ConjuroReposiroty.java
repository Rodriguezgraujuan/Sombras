package container.Sombras.Repositorio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Conjuros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuroReposiroty extends JpaRepository<Conjuros, Long> {
    Conjuros findByClase(Clase clase);
    Conjuros findByNombre(String nombre);
}
