package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "razaEquipamiento")
public class Raza_Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Raza_id", referencedColumnName = "id", nullable = false)
    private Raza raza;
    @ManyToOne
    @JoinColumn(name = "Equipamiento_id", referencedColumnName = "id", nullable = false)
    private Equipamiento equipamiento;
}
