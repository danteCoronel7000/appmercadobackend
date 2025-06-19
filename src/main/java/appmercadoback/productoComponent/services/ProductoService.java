package appmercadoback.productoComponent.services;

import appmercadoback.productoComponent.entitys.ProductoEntity;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoEntity guardarProducto(ProductoEntity producto);

    List<ProductoEntity> obtenerTodos();

    Optional<ProductoEntity> obtenerPorId(Integer id);

    ProductoEntity actualizarProducto(Integer id, ProductoEntity productoActualizado);

    void eliminarProducto(Integer id);
}
