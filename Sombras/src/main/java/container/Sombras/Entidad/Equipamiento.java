package container.Sombras.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "equipamiento")
public class Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "claseEquipamiento")
    @OneToMany(mappedBy = "equipamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clase_Equipamiento> claseEquipamiento;

    public Equipamiento(String name) {
        this.name = name;
    }
    public Equipamiento(){
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Clase_Equipamiento> getClaseEquipamiento() {
        return claseEquipamiento;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setClaseEquipamiento(Set<Clase_Equipamiento> razaEquipamiento) {
        this.claseEquipamiento = razaEquipamiento;
    }
}
