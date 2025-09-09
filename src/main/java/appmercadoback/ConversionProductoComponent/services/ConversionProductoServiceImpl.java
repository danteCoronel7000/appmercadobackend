package appmercadoback.ConversionProductoComponent.services;

import appmercadoback.ConversionProductoComponent.Entity.ConversionProductoEntity;
import appmercadoback.ConversionProductoComponent.repository.ConversionProductoRepository;
import appmercadoback.productoComponent.repositorys.ProductoRepository;
import appmercadoback.unidadMedidaComponent.repository.UnidadMedidaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConversionProductoServiceImpl implements ConversionProductoService {

    private final ConversionProductoRepository conversionRepo;
    private final ProductoRepository productoRepo;
    private final UnidadMedidaRepository unidadRepo;

    public ConversionProductoServiceImpl(ConversionProductoRepository conversionRepo,
                                         ProductoRepository productoRepo,
                                         UnidadMedidaRepository unidadRepo) {
        this.conversionRepo = conversionRepo;
        this.productoRepo = productoRepo;
        this.unidadRepo = unidadRepo;
    }

    @Override
    public ConversionProductoEntity create(ConversionProductoEntity conversion) {
        // (Opcional) validaciones: que existan producto y unidades, factor > 0, etc.
        return conversionRepo.save(conversion);
    }

    @Override
    public ConversionProductoEntity update(Integer id, ConversionProductoEntity conversion) {
        conversion.setId(id);
        return conversionRepo.save(conversion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConversionProductoEntity> findById(Integer id) {
        return conversionRepo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConversionProductoEntity> findByProductoId(Integer productoId) {
        return conversionRepo.findByProductoId(productoId);
    }

    @Override
    public void delete(Integer id) {
        conversionRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal convertir(Integer productoId, Integer unidadCompraId, Integer unidadVentaId, BigDecimal cantidad) {
        // buscamos la conversión exacta (producto + unidades)
        Optional<ConversionProductoEntity> opt = conversionRepo.findByProductoIdAndUnidadCompraIdAndUnidadVentaId(
                productoId, unidadCompraId, unidadVentaId);

        if (opt.isPresent()) {
            BigDecimal factor = opt.get().getFactor();
            // cantidadVenta = cantidadCompra * factor
            return cantidad.multiply(factor);
        }

        // si no existe conversión directa, intentar búsqueda inversa (venta->compra) y dividir
        Optional<ConversionProductoEntity> inv = conversionRepo.findByProductoIdAndUnidadCompraIdAndUnidadVentaId(
                productoId, unidadVentaId, unidadCompraId);

        if (inv.isPresent()) {
            BigDecimal invFactor = inv.get().getFactor();
            // si invFactor = venta/compra para la tupla inversa, entonces
            // cantidadVenta = cantidadCompra / invFactor
            return cantidad.divide(invFactor, 6, BigDecimal.ROUND_HALF_UP);
        }

        throw new IllegalArgumentException("No se encontró conversión para productoId=" + productoId
                + " entre unidades " + unidadCompraId + " y " + unidadVentaId);
    }
}
