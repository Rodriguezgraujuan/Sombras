package container.Sombras.Entidad;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "equipamiento")
public class Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "razaEquipamiento")
    @OneToMany(mappedBy = "equipamiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Raza_Equipamiento> razaEquipamiento;

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

    public Set<Raza_Equipamiento> getRazaEquipamiento() {
        return razaEquipamiento;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRazaEquipamiento(Set<Raza_Equipamiento> razaEquipamiento) {
        this.razaEquipamiento = razaEquipamiento;
    }
}
