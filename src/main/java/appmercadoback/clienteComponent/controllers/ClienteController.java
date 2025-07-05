package appmercadoback.clienteComponent.controllers;

import appmercadoback.clienteComponent.entitys.ClienteEntity;
import appmercadoback.clienteComponent.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/get/crear")
    public ResponseEntity<ClienteEntity> crear(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @GetMapping("/get/todos")
    public ResponseEntity<List<ClienteEntity>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClienteEntity> obtener(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/get/{id}")
    public ResponseEntity<ClienteEntity> actualizar(@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
