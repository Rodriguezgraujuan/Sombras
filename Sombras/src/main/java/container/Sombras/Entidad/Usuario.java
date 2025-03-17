package container.Sombras.Entidad;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="imagen")
    private String imagen;

    @Column(name="rol")
    private String rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario_Personaje> usuarioPersonajes = new HashSet<>();

    public Usuario(String username, String email, String passwod, String rol) {
        this.username = username;
        this.email = email;
        this.password = passwod;
        this.rol = rol;
    }

    public Usuario(){}

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<Usuario_Personaje> getUsuarioPersonajes() {
        return usuarioPersonajes;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getImagen() {
        return imagen;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsuarioPersonajes(Set<Usuario_Personaje> usuarioPersonajes) {
        this.usuarioPersonajes = usuarioPersonajes;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setPassword(String passwod) {
        this.password = passwod;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
