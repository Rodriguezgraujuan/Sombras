package container.Sombras.Entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "salud")
    private String salud;

    @Column(name = "resistencia")
    private String resistencia;

    @Column(name = "tipoDps")
    private String tipoDps;

    @Column(name = "arma")
    private String arma;

    @Column(name = "publico")
    private boolean pulico;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario_Personaje> usuarioPersonajes = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public Clase getClase() {
        return clase;
    }

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getArma() {
        return arma;
    }

    public String getResistencia() {
        return resistencia;
    }

    public String getSalud() {
        return salud;
    }

    public String getTipoDps() {
        return tipoDps;
    }

    public Set<Usuario_Personaje> getUsuarioPersonajes() {
        return usuarioPersonajes;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPulico(boolean pulico) {
        this.pulico = pulico;
    }

    public void setResistencia(String resistencia) {
        this.resistencia = resistencia;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    public void setTipoDps(String tipoDps) {
        this.tipoDps = tipoDps;
    }

    public void setUsuarioPersonajes(Set<Usuario_Personaje> usuarioPersonajes) {
        this.usuarioPersonajes = usuarioPersonajes;
    }
}
