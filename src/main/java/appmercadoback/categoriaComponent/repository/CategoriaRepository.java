package appmercadoback.categoriaComponent.repository;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    // Por si querés buscar una categoría por nombre (útil para validaciones o filtros)
    Optional<CategoriaEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    List<CategoriaEntity> findByNombreContainingIgnoreCase(String nombre);

    // Opción 3: Combinando ambas condiciones (más seguro) - CONSULTA NATIVA
    @Query(value = "SELECT * FROM categoria WHERE parent_id IS NULL AND (is_parent = 1 OR is_parent IS NULL)", nativeQuery = true)
    List<CategoriaEntity> findCategoriasPrincipalesSeguro();
}
