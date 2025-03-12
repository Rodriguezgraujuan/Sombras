package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "claseAtributo")
public class Clase_Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aumento")
    private long aumento;

    @ManyToOne
    @JoinColumn(name = "Clase_id", referencedColumnName = "id", nullable = false)
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "Atributo_id", referencedColumnName = "id", nullable = false)
    private Atributo atributo;
}
