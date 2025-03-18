package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "razaEquipamiento")
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
}
