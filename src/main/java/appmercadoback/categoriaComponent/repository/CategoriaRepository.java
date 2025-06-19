package appmercadoback.categoriaComponent.repository;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    // Por si querés buscar una categoría por nombre (útil para validaciones o filtros)
    Optional<CategoriaEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre);
}
