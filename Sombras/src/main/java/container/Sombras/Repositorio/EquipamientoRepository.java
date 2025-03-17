package container.Sombras.Repositorio;

import container.Sombras.Entidad.Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamientoRepository extends JpaRepository<Equipamiento, Long> {
    Equipamiento findByName(String name);

}
