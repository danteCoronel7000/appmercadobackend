package appmercadoback.pedidoComponent.controllers;

import appmercadoback.pedidoComponent.entitys.PedidoEntity;
import appmercadoback.pedidoComponent.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/get/crear")
    public ResponseEntity<PedidoEntity> crearPedido(@RequestBody PedidoEntity pedidoEntity) {
        return ResponseEntity.ok(pedidoService.guardar(pedidoEntity));
    }

    @GetMapping("/get/listarTodos")
    public ResponseEntity<List<PedidoEntity>> listarPedido() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id, @RequestBody PedidoEntity pedidoEntity) {
        return ResponseEntity.ok(pedidoService.actualizar(id, pedidoEntity));
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id){
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
