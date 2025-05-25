package container.Sombras.Servicio;

import container.Sombras.Entidad.Clase;
import container.Sombras.Entidad.Conjuros;
import container.Sombras.Repositorio.ConjuroReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConjuroService {
    @Autowired
    private ConjuroReposiroty conjuroReposiroty;

    public Conjuros save(Conjuros conjuro){
        return conjuroReposiroty.save(conjuro);
    }
    public Conjuros findById(Long id){
        return conjuroReposiroty.findById(id).orElse(null);
    }
    public void delete(Conjuros conjuro){
        conjuroReposiroty.delete(conjuro);
    }
    public List<Conjuros> findAll(){
        return conjuroReposiroty.findAll();
    }
    public List<Conjuros> findByClase(Clase clase){
        return conjuroReposiroty.findByClase(clase);
    }
    public Conjuros findByNombre(String nombre){
        return conjuroReposiroty.findByNombre(nombre);
    }
}
