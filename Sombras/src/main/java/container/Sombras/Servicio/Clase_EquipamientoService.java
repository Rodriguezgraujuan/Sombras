package container.Sombras.Servicio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Clase_Equipamiento;
import container.Sombras.Entidad.Equipamiento;
import container.Sombras.Repositorio.Clase_EquipamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Clase_EquipamientoService {

    @Autowired
    Clase_EquipamientoRepository claseEquipamientoRepository;

    @Autowired
    ClaseService claseService;

    public Clase_Equipamiento findById(Long id) {
        return claseEquipamientoRepository.findById(id).orElse(null);
    }

    public void save(Clase_Equipamiento claseEquipamiento) {
        claseEquipamientoRepository.save(claseEquipamiento);
    }

    public void delete(Clase_Equipamiento razaEquipamiento) {
        claseEquipamientoRepository.delete(razaEquipamiento);
    }

    public List<Clase_Equipamiento> findAll() {
        return claseEquipamientoRepository.findAll();
    }

    public boolean existsByClaseAndEquipamiento(Clase clase, Equipamiento equipamiento) {
        return claseEquipamientoRepository.existsByClaseAndEquipamiento(clase, equipamiento);
    }
}
