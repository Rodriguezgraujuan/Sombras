package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "razaHabilidad")
public class Raza_Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Raza_id", referencedColumnName = "id", nullable = false)
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "Habilidad_id", referencedColumnName = "id", nullable = false)
    private Habilidad habilidad;


    public Raza_Habilidad(Raza humano, Habilidad acrobacias) {
        this.raza = humano;
        this.habilidad = acrobacias;
    }
    public Raza_Habilidad() {}

    public Long getId() {
        return id;
    }

    public Raza getRaza() {
        return raza;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }
}
