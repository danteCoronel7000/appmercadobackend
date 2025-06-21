package appmercadoback.productoComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.categoriaComponent.repository.CategoriaRepository;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.entitys.Image;
import appmercadoback.productoComponent.repositorys.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    @Autowired
    private ImageService imageService;

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

    //metodos para la gestion de productos con imagenes en la nube (cloudInary)
    @Override
    public ProductoEntity saveProducto(ProductoEntity producto, MultipartFile file) throws IOException {
        // 👇 Validar y buscar la categoría por su ID
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            CategoriaEntity categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + producto.getCategoria().getId()));
            producto.setCategoria(categoria);
        } else {
            throw new RuntimeException("Se debe proporcionar el id de la categoría");
        }

        // 👇 Si hay archivo, procesar la imagen
        if (file != null && !file.isEmpty()) {
            Image image = imageService.uploadImage(file);
            producto.setImage(image);
        }

        return productoRepository.save(producto);
    }

    @Override
    public ProductoEntity updateProductoAndImage(ProductoEntity producto, MultipartFile file) throws IOException {
        // Validar y buscar la categoría por su ID
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            CategoriaEntity categoria = categoriaRepository.findById(producto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + producto.getCategoria().getId()));
            producto.setCategoria(categoria);
        } else {
            throw new RuntimeException("Se debe proporcionar el id de la categoría");
        }

        // Obtener el producto existente por su ID
        ProductoEntity productoExistente = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + producto.getId()));

        // Eliminar imagen anterior si llega una nueva
        if (file != null && !file.isEmpty()) {
            if (productoExistente.getImage() != null) {
                imageService.deleteImage(productoExistente.getImage());
            }
            Image nuevaImagen = imageService.uploadImage(file);
            productoExistente.setImage(nuevaImagen);
        }

        // 👇 Actualizar los demás campos
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setUnidadMedida(producto.getUnidadMedida());
        productoExistente.setPerecedero(producto.getPerecedero());
        productoExistente.setCategoria(producto.getCategoria());

        // 👇 Guardar y retornar el producto actualizado
        return productoRepository.save(productoExistente);
    }



    @Override
    public ProductoEntity updateProducto(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<ProductoEntity> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<ProductoEntity> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void deleteProducto(ProductoEntity producto) throws IOException {
        if (producto.getImage() != null) {
            imageService.deleteImage(producto.getImage());
        }
        productoRepository.deleteById(producto.getId());
    }

    @Override
    public ProductoEntity updateProductoImage(MultipartFile file, ProductoEntity producto) throws IOException {
        if (producto.getImage() != null) {
            imageService.deleteImage(producto.getImage());
        }
        Image newImage = imageService.uploadImage(file);
        producto.setImage(newImage);
        return productoRepository.save(producto);
    }

    @Override
    public List<ProductoEntity> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
