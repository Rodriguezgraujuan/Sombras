package container.Sombras.Repositorio;

import container.Sombras.Entidad.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}
