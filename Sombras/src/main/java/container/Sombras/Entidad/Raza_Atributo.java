package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "razaAtributo")
public class Raza_Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Raza_id", referencedColumnName = "id", nullable = false)
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "Atributo_id", referencedColumnName = "id", nullable = false)
    private Atributo atributo;

    public Raza_Atributo(Raza humano, Atributo todas) {
        this.raza = humano;
        this.atributo = todas;
    }
    public Raza_Atributo() {}

    public Long getId() {
        return id;
    }

    public Raza getRaza() {
        return raza;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }
}
