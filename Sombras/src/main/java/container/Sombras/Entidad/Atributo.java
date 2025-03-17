package container.Sombras.Entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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

    @Column(name = "valor")
    private long valor;

    public Atributo() {
    }
    public Atributo(String name, String descripcion, long valor) {
        this.name = name;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public long getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getName() {
        return name;
    }
}
