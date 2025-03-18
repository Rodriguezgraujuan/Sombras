package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "claseEquipamiento")
public class Clase_Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Clase_id", referencedColumnName = "id", nullable = false)
    private Clase clase;
    @ManyToOne
    @JoinColumn(name = "Equipamiento_id", referencedColumnName = "id", nullable = false)
    private Equipamiento equipamiento;

    public Clase_Equipamiento() {}
    public Clase_Equipamiento(Clase clase, Equipamiento equipamiento) {
        this.clase = clase;
        this.equipamiento = equipamiento;
    }

    public Clase getClase() {
        return clase;
    }

    public Equipamiento getEquipamiento() {
        return equipamiento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public void setEquipamiento(Equipamiento equipamiento) {
        this.equipamiento = equipamiento;
    }

    public Long getId() {
        return id;
    }
}
