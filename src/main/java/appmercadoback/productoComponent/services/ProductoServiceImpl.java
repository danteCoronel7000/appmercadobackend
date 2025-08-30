package appmercadoback.productoComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.categoriaComponent.repository.CategoriaRepository;
import appmercadoback.productoComponent.dtos.ProductoDTO;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.entitys.Image;
import appmercadoback.productoComponent.repositorys.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        productoExistente.setMedida(producto.getMedida());
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

    @Override
    public List<ProductoDTO> buscarPorNombreApp(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

    //lista de productos con dto para la aplicacion movil
    @Override
    public List<ProductoDTO> getProductosDto() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }
    //obtener un producto por id
    @Override
    public ProductoDTO obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id)
                .map(ProductoDTO::new)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> listProductPreferidosOfClient(Long id) {
        List<Object[]> rows = productoRepository.listProductPreferidosOfClientNative(id);

        return rows.stream().map(r -> {
            // r[0]..r[13] deben seguir EXACTAMENTE el orden del SELECT de la query nativa
            Integer idProd = r[0] == null ? null : ((Number) r[0]).intValue();
            String nombre = r[1] == null ? null : r[1].toString();
            String descripcion = r[2] == null ? null : r[2].toString();
            Float precio = null;
            if (r[3] != null) {
                if (r[3] instanceof Number) precio = ((Number) r[3]).floatValue();
                else try { precio = Float.valueOf(r[3].toString()); } catch (Exception ignored) {}
            }
            Boolean perecedero = null;
            if (r[4] != null) {
                if (r[4] instanceof Boolean) perecedero = (Boolean) r[4];
                else perecedero = Boolean.valueOf(r[4].toString());
            }
            String unidadMedida = r[5] == null ? null : r[5].toString();
            String medida = r[6] == null ? null : r[6].toString();
            Integer stockActual = r[7] == null ? null : ((Number) r[7]).intValue();
            Integer stockMin = r[8] == null ? null : ((Number) r[8]).intValue();

            Date fechaVenc = null;
            if (r[9] != null) {
                Object o = r[9];
                if (o instanceof java.util.Date) {
                    fechaVenc = (Date) o;
                } else if (o instanceof java.sql.Date) {
                    fechaVenc = new Date(((java.sql.Date) o).getTime());
                } else if (o instanceof java.sql.Timestamp) {
                    fechaVenc = new Date(((java.sql.Timestamp) o).getTime());
                } else {
                    // último recurso: intentar parsear la cadena (si es necesario)
                    try { fechaVenc = java.sql.Date.valueOf(o.toString()); } catch (Exception ignored) {}
                }
            }

            Integer popularidad = r[10] == null ? null : ((Number) r[10]).intValue();
            String etiquetas = r[11] == null ? null : r[11].toString();
            String categoriaNombre = r[12] == null ? null : r[12].toString();
            String imageUrl = r[13] == null ? null : r[13].toString();

            return new ProductoDTO(
                    idProd, nombre, descripcion, precio, perecedero,
                    unidadMedida, medida, stockActual, stockMin,
                    fechaVenc, popularidad, etiquetas, categoriaNombre, imageUrl
            );
        }).collect(Collectors.toList());
    }

    // En tu servicio
    public Page<ProductoEntity> getProductosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

}
