package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "claseAtributo")
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
}
