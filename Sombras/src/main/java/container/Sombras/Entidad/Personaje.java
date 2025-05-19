package container.Sombras.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "publico")
    private boolean pulico;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "nivel")
    private int nivel;

    @Column(name = "inteligencia")
    private int inteligencia;

    @Column(name = "fuerza")
    private int fuerza;

    @Column(name = "destreza")
    private int destreza;

    @Column(name = "constitucion")
    private int constitucion;

    @Column(name = "sabiduria")
    private int sabiduria;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "raza_id", nullable = false)
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    public Personaje(String nombre, String apellido, int nivel){
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
    }

    public Personaje(String nombre, String apellido, Clase clase, Raza raza, String descripcion, String imagen, int nivel, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.clase = clase;
        this.raza = raza;
        this.imagen = imagen;
        this.nivel = nivel;
        this.usuario = usuario;
    }

    public Personaje(boolean publico,String nombre, String apellido, Clase clase, Raza raza, String descripcion, String imagen, int nivel, int destreza, int constitucion, int inteligencia, int fuerza, int sabiduria, Usuario usuario) {
        pulico = publico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.clase = clase;
        this.raza = raza;
        this.imagen = imagen;
        this.nivel = nivel;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.inteligencia = inteligencia;
        this.fuerza = fuerza;
        this.sabiduria = sabiduria;
        this.usuario = usuario;
    }

    public Personaje() {}

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

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

    public boolean isPulico() {
        return pulico;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public int getNivel() {
        return nivel;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getSabiduria() {
        return sabiduria;
    }

    public void setConstitucion(int constitucion) {
        this.constitucion = constitucion;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setSabiduria(int sabiduria) {
        this.sabiduria = sabiduria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
