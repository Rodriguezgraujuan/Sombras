package container.Sombras.Repositorio;

import container.Sombras.Entidad.Raza_Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Raza_EquipamientoRepository extends JpaRepository<Raza_Equipamiento, Long> {
}
