package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "atributo")
public class Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descripcion")
    private String descripcion;
}
