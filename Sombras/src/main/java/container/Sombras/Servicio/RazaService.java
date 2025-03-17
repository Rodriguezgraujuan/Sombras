package container.Sombras.Servicio;

import container.Sombras.Entidad.Raza;
import container.Sombras.Repositorio.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RazaService {
    @Autowired
    RazaRepository razaRepository;

    public Raza findById(Long id){
        return razaRepository.findById(id).orElse(null);
    }

    public Raza save(Raza raza){
        return razaRepository.save(raza);
    }
    public void delete(Raza raza){
        razaRepository.delete(raza);
    }
    public List<Raza> findAll(){
        return razaRepository.findAll();
    }
    public Raza findByName(String name){
        return razaRepository.findByName(name);
    }

}
