package appmercadoback.ConversionProductoComponent.repository;



import appmercadoback.ConversionProductoComponent.Entity.ConversionProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ConversionProductoRepository extends JpaRepository<ConversionProductoEntity, Integer> {
    List<ConversionProductoEntity> findByProductoId(Integer productoId);
    Optional<ConversionProductoEntity> findByProductoIdAndUnidadCompraIdAndUnidadVentaId(
            Integer productoId, Integer unidadCompraId, Integer unidadVentaId);
}
