package container.Sombras.Servicio;

import container.Sombras.Entidad.Habilidad;
import container.Sombras.Repositorio.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService {
    @Autowired
    private HabilidadRepository habilidadRepository;

    public Habilidad findById(Long id){
        return habilidadRepository.findById(id).get();
    }
    public void save(Habilidad habilidad){
        habilidadRepository.save(habilidad);
    }
    public void delete(Habilidad habilidad){
        habilidadRepository.delete(habilidad);
    }
    public List<Habilidad> findAll(){
        return habilidadRepository.findAll();
    }
    public Habilidad findByName(String name){
        return habilidadRepository.findByName(name);
    }

}
