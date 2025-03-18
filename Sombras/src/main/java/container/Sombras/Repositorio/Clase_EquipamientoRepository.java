package container.Sombras.Repositorio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Clase_Equipamiento;
import container.Sombras.Entidad.Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Clase_EquipamientoRepository extends JpaRepository<Clase_Equipamiento, Long> {
    boolean existsByClaseAndEquipamiento(Clase clase, Equipamiento equipamiento);

}
