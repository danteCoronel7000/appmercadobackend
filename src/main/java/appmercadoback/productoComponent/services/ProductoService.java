package appmercadoback.productoComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.productoComponent.dtos.ProductoDTO;
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

    ProductoEntity updateProductoAndImage(ProductoEntity producto, MultipartFile file) throws IOException;

    ProductoEntity updateProducto(ProductoEntity producto);

    List<ProductoEntity> getProductos();

    List<ProductoDTO> getProductosDto();

    Optional<ProductoEntity> getProductoById(Integer id);

    void deleteProducto(ProductoEntity producto) throws IOException;

    ProductoEntity updateProductoImage(MultipartFile file, ProductoEntity producto) throws IOException;

    List<ProductoEntity> buscarPorNombre(String nombre);
    //retorna todos los productos pertenecientes a una categoria
    List<ProductoDTO> obtenerProductosPorCategoria(Integer categoriaId);
    //obtener un producto por id
    ProductoDTO obtenerProductoPorId(Integer id);
}
