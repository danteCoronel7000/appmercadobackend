package appmercadoback.productoComponent.repositorys;


import appmercadoback.productoComponent.entitys.ProductoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    @Query(
            value = """
            SELECT * 
            FROM producto p
            WHERE p.categoria_id IN (:categorias)
              AND p.id NOT IN (
                  SELECT i.producto_id
                  FROM interaccion_usuario i
                  WHERE i.cliente_id = :clienteId
                    AND i.tipo = 'VISITA'
              )
            ORDER BY p.fecha_creacion DESC
            LIMIT :limit
        """,
            nativeQuery = true
    )
    List<ProductoEntity> findRecomendadosByCategoriasNative(
            @Param("categorias") List<Long> categorias,
            @Param("clienteId") Long clienteId,
            @Param("limit") int limit
    );
    // Ejemplos de métodos personalizados (por si quieres ir más allá)

    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);

    List<ProductoEntity> findByCategoria_Nombre(String categoriaNombre);

    List<ProductoEntity> findByPerecederoTrue();

    List<ProductoEntity> findByStockActualLessThan(Integer stockMinimo);

    List<ProductoEntity> findByFechaVencimientoBefore(java.util.Date fecha);
    //retorna todos los productos pertenecientes a una categoria
    List<ProductoEntity> findByCategoriaId(Integer categoriaId);
}
