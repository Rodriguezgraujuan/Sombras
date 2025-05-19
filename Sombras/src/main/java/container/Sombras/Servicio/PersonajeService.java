package container.Sombras.Servicio;

import container.Sombras.Entidad.Personaje;
import container.Sombras.Repositorio.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    public Personaje findById(Long id){
        return personajeRepository.findById(id).orElse(null);
    }
    public Personaje save(Personaje personaje){
        if(personaje == null){
            throw new IllegalArgumentException("El personaje no puede ser nulo");
        }
        return personajeRepository.save(personaje);
    }
    public void delete(Personaje personaje){
        personajeRepository.delete(personaje);
    }
    public List<Personaje> findAll(){
        return personajeRepository.findAll();
    }
    public Personaje findByNombre(String nombre){
        return personajeRepository.findByNombre(nombre);
    }

}
