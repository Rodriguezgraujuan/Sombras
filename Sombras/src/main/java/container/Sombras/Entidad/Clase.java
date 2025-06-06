package container.Sombras.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clase_Equipamiento> claseEquipamientos;

    @Column(name = "pGolpe")
    private String pGolpe;

    @Column(name = "lanzador")
    private String lanzador;

    @Column(name = "descripcion")
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Conjuros> conjuros;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Personaje> personajes;


    public Clase(String clase, String pGolpe, String lanzador, Set<Conjuros> conjuros, String descripcion) {
        this.nombre = clase;
        this.pGolpe = pGolpe;
        this.personajes = new HashSet<>();
        this.lanzador = lanzador;
        this.conjuros = conjuros;
        this.claseEquipamientos = new HashSet<>();
        this.descripcion = descripcion;
    }

    public Clase(){}

    public String getpGolpe() {
        return pGolpe;
    }

    public void setpGolpe(String pGolpe) {
        this.pGolpe = pGolpe;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String clase) {
        this.nombre = clase;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }

    public Set<Clase_Equipamiento> getClaseEquipamientos() {
        return claseEquipamientos;
    }

    public Set<Conjuros> getConjuros() {
        return conjuros;
    }

    public String getLanzador() {
        return lanzador;
    }

    public void setClaseEquipamientos(Set<Clase_Equipamiento> claseEquipamientos) {
        this.claseEquipamientos = claseEquipamientos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setConjuros(Set<Conjuros> conjuros) {
        this.conjuros = conjuros;
    }

    public void setLanzador(String lanzador) {
        this.lanzador = lanzador;
    }

    public void addConjuro(Conjuros conjuro){
        this.conjuros.add(conjuro);
    }

    public void addPersonaje(Personaje personaje){
        this.personajes.add(personaje);
    }
    public void addEquipamiento(Clase_Equipamiento equipamiento){
        this.claseEquipamientos.add(equipamiento);
    }
}
