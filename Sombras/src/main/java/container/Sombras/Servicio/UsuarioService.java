package container.Sombras.Servicio;

import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }


}
