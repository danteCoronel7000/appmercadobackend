package appmercadoback.ConversionProductoComponent.services;

import appmercadoback.ConversionProductoComponent.Entity.ConversionProductoEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ConversionProductoService {
    ConversionProductoEntity create(ConversionProductoEntity conversion);
    ConversionProductoEntity update(Integer id, ConversionProductoEntity conversion);
    Optional<ConversionProductoEntity> findById(Integer id);
    List<ConversionProductoEntity> findByProductoId(Integer productoId);
    void delete(Integer id);

    // conversión: devuelve la cantidad convertida (según convención factor = venta / compra)
    BigDecimal convertir(Integer productoId, Integer unidadCompraId, Integer unidadVentaId, BigDecimal cantidad);
}
