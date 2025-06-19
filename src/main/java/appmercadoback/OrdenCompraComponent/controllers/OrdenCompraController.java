package appmercadoback.OrdenCompraComponent.controllers;

import appmercadoback.OrdenCompraComponent.entitys.OrdenCompraEntity;
import appmercadoback.OrdenCompraComponent.services.OrdenCompraService;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
@RequiredArgsConstructor
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    @GetMapping
    public ResponseEntity<List<OrdenCompraEntity>> getAll() {
        return ResponseEntity.ok(ordenCompraService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraEntity> obtenerPorId(@PathVariable Integer id) {
        return ordenCompraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdenCompraEntity> create(@RequestBody OrdenCompraEntity orden) {
        return ResponseEntity.ok(ordenCompraService.crear(orden));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompraEntity> update(@PathVariable Integer id, @RequestBody OrdenCompraEntity orden) {
        return ResponseEntity.ok(ordenCompraService.actualizar(id, orden));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
