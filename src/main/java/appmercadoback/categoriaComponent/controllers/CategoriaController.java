package appmercadoback.categoriaComponent.controllers;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.categoriaComponent.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

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

    @GetMapping("/buscar")
    public ResponseEntity<List<CategoriaEntity>> buscarPorNombre(@RequestParam String nombre) {
        List<CategoriaEntity> resultado = categoriaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultado);
    }

}
