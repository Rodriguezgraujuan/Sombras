package container.Sombras.Controller;

import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import container.Sombras.Servicio.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.regex.Pattern;

@Controller
public class UsuarioController {

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/register")
    @Transactional
    ResponseEntity<?> create(@RequestBody Usuario user) {
        if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Faltan datos requeridos");
        } else if (usuarioService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("El correo ya esta registrado");
        }else if (user.getPassword() == null || user.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body("Longitud de contraseña incorrecta, minimo 8 caracteres");
        }else{
            try {
                user.setRol("USER");
                if (pattern.matcher(user.getPassword()).matches()) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    usuarioService.save(user);

                    return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con éxito");
                }else {
                    return ResponseEntity.badRequest().body("Contraseña invalida");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");
            }
        }
    }
}
