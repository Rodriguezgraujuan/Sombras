package container.Sombras.Servicio;

import container.Sombras.Entidad.Equipamiento;
import container.Sombras.Repositorio.EquipamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamientoService {
    @Autowired
    EquipamientoRepository equipamientoRepository;

    public Equipamiento findById(Long id){
        return equipamientoRepository.findById(id).orElse(null);
    }
    public List<Equipamiento> findAll(){
        return equipamientoRepository.findAll();
    }
    public Equipamiento save(Equipamiento equipamiento){
        return equipamientoRepository.save(equipamiento);
    }
    public void delete(Equipamiento equipamiento){
        equipamientoRepository.delete(equipamiento);
    }
    public Equipamiento findByName(String name){
        return equipamientoRepository.findByName(name);
    }
}
