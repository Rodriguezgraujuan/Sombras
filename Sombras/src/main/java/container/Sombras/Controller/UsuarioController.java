package container.Sombras.Controller;

import container.Sombras.Entidad.Usuario;
import container.Sombras.Repositorio.UsuarioRepository;
import container.Sombras.Servicio.UsuarioService;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

@RestController
public class UsuarioController {

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

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
                    user.setStartDate(LocalDate.now().getYear()+" "+LocalDate.now().getMonth());
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

    @GetMapping("/userInfo")
    Usuario userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario;
        if (authentication.getPrincipal() instanceof OAuth2User) {
            usuario = usuarioService.findByEmail(((OAuth2User) authentication.getPrincipal()).getAttribute("email"));
        }else {
            usuario = usuarioService.findByEmail(authentication.getName());
        }
        return usuario;
    }
}
