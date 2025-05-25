package container.Sombras.Repositorio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Clase_Equipamiento;
import container.Sombras.Entidad.Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Clase_EquipamientoRepository extends JpaRepository<Clase_Equipamiento, Long> {
    boolean existsByClaseAndEquipamiento(Clase clase, Equipamiento equipamiento);
    List<Clase_Equipamiento> findByClase(Clase clase);

}
