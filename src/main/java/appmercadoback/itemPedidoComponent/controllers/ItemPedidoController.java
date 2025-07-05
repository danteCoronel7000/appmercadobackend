package appmercadoback.itemPedidoComponent.controllers;

import appmercadoback.itemPedidoComponent.entitys.ItemPedidoEntity;
import appmercadoback.itemPedidoComponent.services.ItemPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-pedidos")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @PostMapping("/get/crear")
    public ResponseEntity<ItemPedidoEntity> crear(@RequestBody ItemPedidoEntity item) {
        return ResponseEntity.ok(itemPedidoService.guardar(item));
    }

    @GetMapping("/get/todos")
    public ResponseEntity<List<ItemPedidoEntity>> listar() {
        return ResponseEntity.ok(itemPedidoService.listarTodos());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ItemPedidoEntity> obtener(@PathVariable Long id) {
        return itemPedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/get/{id}")
    public ResponseEntity<ItemPedidoEntity> actualizar(@PathVariable Long id, @RequestBody ItemPedidoEntity item) {
        return ResponseEntity.ok(itemPedidoService.actualizar(id, item));
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        itemPedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
