package container.Sombras.Entidad;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "raza")
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tall")
    private double tall;

    @Column(name = "velocity")
    private int velocity;

    @Column(name = "hability")
    @OneToMany(mappedBy = "raza", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Raza_Habilidad> hability;

    @Column(name = "atributo")
    @OneToMany(mappedBy = "raza", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Raza_Atributo> atributoRaza = new HashSet<>();

    @Column(name = "personaje")
    @OneToMany(mappedBy = "raza", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Personaje> personaje = new HashSet<>();

    public Raza() {}
    public Raza(String name, double tall, int velocity) {
        this.name = name;
        this.tall = tall;
        this.velocity = velocity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTall() {
        return tall;
    }

    public int getVelocity() {
        return velocity;
    }

    public Set<Personaje> getPersonaje() {
        return personaje;
    }

    public Set<Raza_Atributo> getAtributoRaza() {
        return atributoRaza;
    }

    public Set<Raza_Habilidad> getHability() {
        return hability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAtributoRaza(Set<Raza_Atributo> atributoRaza) {
        this.atributoRaza = atributoRaza;
    }

    public void setHability(Set<Raza_Habilidad> hability) {
        this.hability = hability;
    }

    public void setTall(double tall) {
        this.tall = tall;
    }

    public void setPersonaje(Set<Personaje> personaje) {
        this.personaje = personaje;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
