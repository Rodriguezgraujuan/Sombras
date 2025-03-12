package container.Sombras.Entidad;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "salud")
    private String salud;

    @Column(name = "resistencia")
    private String resistencia;

    @Column(name = "tipoDps")
    private String tipoDps;

    @Column(name = "arma")
    private String arma;

    @Column(name = "publico")
    private boolean pulico;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario_Personaje> usuarioPersonajes = new HashSet<>();

}
