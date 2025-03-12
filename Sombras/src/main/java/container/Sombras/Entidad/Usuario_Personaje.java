package container.Sombras.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarioPersonaje")
public class Usuario_Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "personaje_id", referencedColumnName = "id", nullable = false)
    private Personaje personaje;
}
