package appmercadoback.productoComponent.repositorys;


import appmercadoback.productoComponent.entitys.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
//retorna la lista personalizada de productos para cada cliente
    @Query(value = "\n" +
            "WITH categorias_usuario AS (\n" +
            "  SELECT DISTINCT pc.id_categoria\n" +
            "  FROM preferencias_cliente pc\n" +
            "  WHERE pc.id_cliente = :id  \n" +
            "),\n" +
            "productos_aleatorios AS (\n" +
            "  SELECT \n" +
            "      p.id,                                \n" +
            "      p.nombre,                            \n" +
            "      p.descripcion,                       \n" +
            "      p.precio,                            \n" +
            "      p.perecedero,                        \n" +
            "      p.unidad_medida,                     \n" +
            "      p.medida,                            \n" +
            "      p.stock_actual,                      \n" +
            "      p.stock_min,                         \n" +
            "      p.fecha_vencimiento,                 \n" +
            "      p.popularidad,                       \n" +
            "      p.etiquetas,                         \n" +
            "      p.categoria_id,                      \n" +
            "      p.image_id,                          \n" +
            "      ROW_NUMBER() OVER (\n" +
            "          PARTITION BY p.categoria_id \n" +
            "          ORDER BY random()\n" +
            "      ) AS rn                              \n" +
            "  FROM producto p\n" +
            "  JOIN categorias_usuario cu \n" +
            "    ON p.categoria_id = cu.id_categoria\n" +
            ")\n" +
            "SELECT \n" +
            "  pa.id,\n" +
            "  pa.nombre,\n" +
            "  pa.descripcion,\n" +
            "  pa.precio,\n" +
            "  pa.perecedero,\n" +
            "  pa.unidad_medida,\n" +
            "  pa.medida,\n" +
            "  pa.stock_actual,\n" +
            "  pa.stock_min,\n" +
            "  pa.fecha_vencimiento,\n" +
            "  pa.popularidad,\n" +
            "  pa.etiquetas,\n" +
            "  c.nombre AS categoriaNombre,             \n" +
            "  i.image_url                               \n" +
            "FROM productos_aleatorios pa\n" +
            "JOIN categoria c \n" +
            "  ON c.id = pa.categoria_id\n" +
            "LEFT JOIN imagen i                          \n" +
            "  ON i.id = pa.image_id\n" +
            "WHERE pa.rn <= 3\n" +
            "ORDER BY c.nombre, pa.nombre;",
            nativeQuery = true)
    List<Object[]> listProductPreferidosOfClientNative(@Param("id") Long id);


    // Ejemplos de métodos personalizados (por si quieres ir más allá)

    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);

    List<ProductoEntity> findByCategoria_Nombre(String categoriaNombre);

    List<ProductoEntity> findByPerecederoTrue();

    List<ProductoEntity> findByStockActualLessThan(Integer stockMinimo);

    List<ProductoEntity> findByFechaVencimientoBefore(java.util.Date fecha);
    //retorna todos los productos pertenecientes a una categoria
    List<ProductoEntity> findByCategoriaId(Integer categoriaId);
}
