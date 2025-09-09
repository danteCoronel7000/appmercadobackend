package appmercadoback.productoComponent.services;

import appmercadoback.productoComponent.dtos.ProductoDTO;
import appmercadoback.productoComponent.dtos.ProductoDtoForWeb;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    //buscar para la aplicacion web
    List<ProductoEntity> buscarPorNombre(String nombre);
    //buscar productos para la aplicacion movil
    List<ProductoDTO> buscarPorNombreApp(String nombre);
    //retorna todos los productos pertenecientes a una categoria
    List<ProductoDTO> obtenerProductosPorCategoria(Integer categoriaId);
    List<ProductoDtoForWeb> getAllProductosPagDto();
    //obtener un producto por id
    ProductoDTO obtenerProductoPorId(Integer id);
    //obtener producto por id
    ProductoDtoForWeb obtenerProductoPorIdForWS(Integer id);
    //retorna la lista personalizada de productos para cada cliente
        List<ProductoDTO> listProductPreferidosOfClient(Long id);
        //retorna lista de productos paginado
    Page<ProductoDtoForWeb> getProductosPaginados(Pageable pageable);
}
