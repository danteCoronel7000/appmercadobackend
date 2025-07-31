package appmercadoback.productoComponent.controllers;


import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.productoComponent.dtos.ProductoDTO;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final SimpMessagingTemplate messagingTemplate;

    /*
    @PostMapping
    public ResponseEntity<ProductoEntity> crearProducto(@RequestBody ProductoEntity producto) {
        ProductoEntity nuevoProducto = productoService.guardarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoEntity>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoEntity> obtenerPorId(@PathVariable Integer id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoEntity> actualizarProducto(@PathVariable Integer id,
                                                             @RequestBody ProductoEntity productoActualizado) {
        try {
            ProductoEntity actualizado = productoService.actualizarProducto(id, productoActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
*/
    //endpoint para manejar los productos con imagenes
    // Guardar un producto con imagen
    @PostMapping("save")
    public ResponseEntity<ProductoEntity> saveProducto(
            @RequestPart("producto") ProductoEntity producto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            ProductoEntity savedProducto = productoService.saveProducto(producto, file);
            Integer id = savedProducto.getId();
            ProductoDTO productoDTO = productoService.obtenerProductoPorId(id);
            messagingTemplate.convertAndSend("/topic/productos", productoDTO); //notificamos a los clientes fronEnd  del registro
            return new ResponseEntity<>(savedProducto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 MOSTRÁ EL ERROR EN CONSOLA
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    // Guardar un producto con imagen
    @PutMapping("update")
    public ResponseEntity<ProductoEntity> updateProductoAndImage(
            @RequestPart("producto") ProductoEntity producto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            ProductoEntity savedProducto = productoService.updateProductoAndImage(producto, file);

            Integer id = savedProducto.getId();
            ProductoDTO productoDTO = productoService.obtenerProductoPorId(id);
            messagingTemplate.convertAndSend("/topic/productos", productoDTO);

            return new ResponseEntity<>(savedProducto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 MOSTRÁ EL ERROR EN CONSOLA
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Actualizar solo la imagen de un producto
    @PutMapping("/{id}/image")
    public ResponseEntity<ProductoEntity> updateProductoImage(
            @PathVariable Integer id,
            @RequestPart("file") MultipartFile file) throws IOException {
        Optional<ProductoEntity> producto = productoService.getProductoById(id);
        if (producto.isPresent()) {
            ProductoEntity updatedProducto = productoService.updateProductoImage(file, producto.get());
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar los datos del producto
    @PutMapping
    public ResponseEntity<ProductoEntity> updateProducto(@RequestBody ProductoEntity producto) {
        try {
            ProductoEntity updatedProducto = productoService.updateProducto(producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener todos los productos
    @GetMapping("/get/all")
    public ResponseEntity<List<ProductoEntity>> getAllProductos() {
        return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);
    }

    @GetMapping("/get/all/dto")
    public ResponseEntity<List<ProductoDTO>> getAllProductosDtoApp(){
        return new ResponseEntity<>(productoService.getProductosDto(), HttpStatus.OK);
    }

    // Obtener un producto por ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductoEntity> getProductoById(@PathVariable Integer id) {
        Optional<ProductoEntity> producto = productoService.getProductoById(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) throws IOException {
        Optional<ProductoEntity> producto = productoService.getProductoById(id);
        if (producto.isPresent()) {
            productoService.deleteProducto(producto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/buscar")
    public ResponseEntity<List<ProductoEntity>> searchByName(@RequestParam String nombre){
        List<ProductoEntity> resultado = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultado);
    }
    //retorna todos los productos pertenecientes a una categoria
    @GetMapping("/get/categoria/{id}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductPorCategoria(@PathVariable Integer id) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(id);
        return ResponseEntity.ok(productos);
    }
    ////obtener un producto por id para el app movil
    @GetMapping("/get/producto/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Integer id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }
}