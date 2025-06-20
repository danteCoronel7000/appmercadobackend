package appmercadoback.productoComponent.services;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoEntity guardarProducto(ProductoEntity producto);

    List<ProductoEntity> obtenerTodos();

    Optional<ProductoEntity> obtenerPorId(Integer id);

    ProductoEntity actualizarProducto(Integer id, ProductoEntity productoActualizado);

    void eliminarProducto(Integer id);

    //servicios para trabajar el producto mas la imagen

    ProductoEntity saveProducto(ProductoEntity producto, MultipartFile file) throws IOException;

    ProductoEntity updateProducto(ProductoEntity producto);

    List<ProductoEntity> getProductos();

    Optional<ProductoEntity> getProductoById(Integer id);

    void deleteProducto(ProductoEntity producto) throws IOException;

    ProductoEntity updateProductoImage(MultipartFile file, ProductoEntity producto) throws IOException;
}
