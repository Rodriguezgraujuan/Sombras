package container.Sombras.Servicio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Repositorio.ClaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {
    @Autowired
    private ClaseRepository claseRepository;

    public Clase findById(Long id){
        return claseRepository.findById(id).orElse(null);
    }
    public void save(Clase clase){
        claseRepository.save(clase);
    }
    public void delete(Clase clase){
        claseRepository.delete(clase);
    }
    public List<Clase> findAll(){
        return claseRepository.findAll();
    }

    public Clase findByNombre(String clase){
        return claseRepository.findByNombre(clase);
    }
}
