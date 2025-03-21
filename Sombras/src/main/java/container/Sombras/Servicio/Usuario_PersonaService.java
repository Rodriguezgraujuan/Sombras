package container.Sombras.Servicio;

import container.Sombras.Entidad.Usuario_Personaje;
import container.Sombras.Repositorio.Usuario_PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Usuario_PersonaService {
    @Autowired
    Usuario_PersonajeRepository usuario_personajeRepository;

    public Optional<Usuario_Personaje> findById(Long id){
        return usuario_personajeRepository.findById(id);
    }
    public Usuario_Personaje save(Usuario_Personaje usuario_personaje){
        return usuario_personajeRepository.save(usuario_personaje);
    }
    public void delete(Usuario_Personaje usuario_personaje){
        usuario_personajeRepository.delete(usuario_personaje);
    }
    public List<Usuario_Personaje> findAll(){
        return  usuario_personajeRepository.findAll();
    }
}
