package appmercadoback.categoriaComponent.controllers;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.categoriaComponent.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

/*
    @PostMapping("/crear")
    public ResponseEntity<CategoriaEntity> crearCategoria(@RequestBody CategoriaEntity categoria) {
        CategoriaEntity nuevaCategoria = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaEntity>> obtenerTodas() throws InterruptedException {
//        Thread.sleep(1000); //simula delay de dos segundos
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> obtenerPorId(@PathVariable Integer id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntity> actualizarCategoria(@PathVariable Integer id,
                                                               @RequestBody CategoriaEntity categoriaActualizada) {
        try {
            CategoriaEntity actualizada = categoriaService.actualizarCategoria(id, categoriaActualizada);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE alternativo: Solo eliminar sin retornar (más tradicional)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok().build(); // Cambiado a 200 OK
    }
    */
    //metodos para categorias con imaganes en cloudinary
// Guardar una categoría con imagen
@PostMapping("save")
public ResponseEntity<CategoriaEntity> saveCategoria(
        @RequestPart("categoria") CategoriaEntity categoria,
        @RequestPart(value = "file", required = false) MultipartFile file) {
    try {
        CategoriaEntity savedCategoria = categoriaService.saveCategoria(categoria, file);
        return new ResponseEntity<>(savedCategoria, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace(); // 👈 MOSTRÁ EL ERROR EN CONSOLA
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    // Actualizar una categoría con imagen
    @PutMapping("update")
    public ResponseEntity<CategoriaEntity> updateCategoriaAndImage(
            @RequestPart("categoria") CategoriaEntity categoria,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            CategoriaEntity updatedCategoria = categoriaService.updateCategoriaAndImage(categoria, file);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar solo la imagen de una categoría
    @PutMapping("/{id}/image")
    public ResponseEntity<CategoriaEntity> updateCategoriaImage(
            @PathVariable Integer id,
            @RequestPart("file") MultipartFile file) throws IOException {
        Optional<CategoriaEntity> categoria = categoriaService.getCategoriaById(id);
        if (categoria.isPresent()) {
            CategoriaEntity updatedCategoria = categoriaService.updateCategoriaImage(file, categoria.get());
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar solo los datos de la categoría
    @PutMapping
    public ResponseEntity<CategoriaEntity> updateCategoria(@RequestBody CategoriaEntity categoria) {
        try {
            CategoriaEntity updatedCategoria = categoriaService.updateCategoria(categoria);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener todas las categorías
    @GetMapping("/get")
    public ResponseEntity<List<CategoriaEntity>> getAllCategorias() {
        return new ResponseEntity<>(categoriaService.getCategorias(), HttpStatus.OK);
    }

    // Obtener una categoría por ID
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoriaEntity> getCategoriaById(@PathVariable Integer id) {
        Optional<CategoriaEntity> categoria = categoriaService.getCategoriaById(id);
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) throws IOException {
        Optional<CategoriaEntity> categoria = categoriaService.getCategoriaById(id);
        if (categoria.isPresent()) {
            categoriaService.deleteCategoria(categoria.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get/buscar")
    public ResponseEntity<List<CategoriaEntity>> buscarPorNombre(@RequestParam String nombre) {
        List<CategoriaEntity> resultado = categoriaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultado);
    }

}
