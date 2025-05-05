package container.Sombras.Repositorio;

import container.Sombras.Entidad.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Raza_AtributoRepository extends JpaRepository<Raza_Atributo, Long> {
    boolean existsByRazaAndAtributo(Raza raza, Atributo atributo);
    List<Raza_Atributo> findByRaza(Raza raza);
}
