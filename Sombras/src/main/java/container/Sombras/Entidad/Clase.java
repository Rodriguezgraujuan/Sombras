package container.Sombras.Entidad;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@Entity
@Table(name="clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clase")
    private String clase;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clase_Equipamiento> claseEquipamientos;

    @Column(name = "pGolpe")
    private String pGolpe;

    @Column(name = "lanzador")
    private String lanzador;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Conjuros> conjuros;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Personaje> personajes;


    public Clase(String clase, String pGolpe) {
        this.clase = clase;
        this.pGolpe = pGolpe;
        this.personajes = new HashSet<>();
    }

    public String getpGolpe() {
        return pGolpe;
    }

    public void setpGolpe(String pGolpe) {
        this.pGolpe = pGolpe;
    }

    public Long getId() {
        return id;
    }

    public String getClase() {
        return clase;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }
}
