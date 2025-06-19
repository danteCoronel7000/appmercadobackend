package appmercadoback.productoComponent.repositorys;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    // Ejemplos de métodos personalizados (por si quieres ir más allá)

    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);

    List<ProductoEntity> findByCategoria_Nombre(String categoriaNombre);

    List<ProductoEntity> findByPerecederoTrue();

    List<ProductoEntity> findByStockActualLessThan(Integer stockMinimo);

    List<ProductoEntity> findByFechaVencimientoBefore(java.util.Date fecha);
}
