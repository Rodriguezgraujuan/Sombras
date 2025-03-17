package container.Sombras.Repositorio;

import container.Sombras.Entidad.Raza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RazaRepository extends JpaRepository<Raza, Long> {
    Raza findByName(String name);
}
