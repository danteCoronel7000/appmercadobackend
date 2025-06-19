package appmercadoback.productoComponent.services;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.repositorys.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    @Override
    public ProductoEntity guardarProducto(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<ProductoEntity> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<ProductoEntity> obtenerPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public ProductoEntity actualizarProducto(Integer id, ProductoEntity productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setPerecedero(productoActualizado.getPerecedero());
                    producto.setUnidadMedida(productoActualizado.getUnidadMedida());
                    producto.setStockActual(productoActualizado.getStockActual());
                    producto.setStockMin(productoActualizado.getStockMin());
                    producto.setFechaVencimiento(productoActualizado.getFechaVencimiento());
                    producto.setPopularidad(productoActualizado.getPopularidad());
                    producto.setEtiquetas(productoActualizado.getEtiquetas());
                    producto.setCategoria(productoActualizado.getCategoria());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public void eliminarProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
