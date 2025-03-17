package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "razaHabilidad")
public class Raza_Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Raza_id", referencedColumnName = "id", nullable = false)
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "Habilidad_id", referencedColumnName = "id", nullable = false)
    private Habilidad habilidad;


}
