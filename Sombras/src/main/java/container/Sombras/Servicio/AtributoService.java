package container.Sombras.Servicio;

import container.Sombras.Entidad.Atributo;
import container.Sombras.Repositorio.AtributoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtributoService {
    @Autowired
    private AtributoRepository atributoRepository;

    public Atributo findById(Long id){
        return atributoRepository.findById(id).orElse(null);
    }
    public Atributo save(Atributo atributo){
        return atributoRepository.save(atributo);
    }
    public void delete(Atributo atributo){
        atributoRepository.delete(atributo);
    }
    public List<Atributo> findAll(){
        return atributoRepository.findAll();
    }
    public Atributo findByName(String name){
        return atributoRepository.findByName(name);
    }
}
