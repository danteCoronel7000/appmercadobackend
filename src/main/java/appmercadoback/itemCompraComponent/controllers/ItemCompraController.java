package appmercadoback.itemCompraComponent.controllers;


import appmercadoback.itemCompraComponent.entitys.ItemCompraEntity;
import appmercadoback.itemCompraComponent.services.ItemCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemCompraController {

    private final ItemCompraService itemCompraService;

    @PostMapping
    public ResponseEntity<ItemCompraEntity> guardarItem(@RequestBody ItemCompraEntity itemCompra) {
        ItemCompraEntity guardado = itemCompraService.guardarItem(itemCompra);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public ResponseEntity<List<ItemCompraEntity>> listarItems() {
        return ResponseEntity.ok(itemCompraService.listarItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCompraEntity> obtenerItemPorId(@PathVariable Integer id) {
        ItemCompraEntity item = itemCompraService.obtenerPorId(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Integer id) {
        itemCompraService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }
}
