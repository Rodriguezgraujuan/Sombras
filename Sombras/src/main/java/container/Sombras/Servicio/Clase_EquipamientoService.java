package container.Sombras.Servicio;

import container.Sombras.Entidad.Clase_Equipamiento;
import container.Sombras.Repositorio.Clase_EquipamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Clase_EquipamientoService {

    @Autowired
    Clase_EquipamientoRepository razaEquipamientoRepository;

    public Clase_Equipamiento findById(Long id){
        return razaEquipamientoRepository.findById(id).orElse(null);
    }
    public void save(Clase_Equipamiento razaEquipamiento){
        razaEquipamientoRepository.save(razaEquipamiento);
    }
    public void delete(Clase_Equipamiento razaEquipamiento){
        razaEquipamientoRepository.delete(razaEquipamiento);
    }
    public List<Clase_Equipamiento> findAll(){
        return razaEquipamientoRepository.findAll();
    }
}
