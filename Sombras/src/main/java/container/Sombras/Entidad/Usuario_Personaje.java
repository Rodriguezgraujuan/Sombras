package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarioPersonaje")
public class Usuario_Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "personaje_id", referencedColumnName = "id", nullable = false)
    private Personaje personaje;

    public Usuario_Personaje() {}
    public Usuario_Personaje(Usuario usuario, Personaje personaje) {
        this.usuario = usuario;
        this.personaje = personaje;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Personaje getPersonaje() {
        return personaje;
    }
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
