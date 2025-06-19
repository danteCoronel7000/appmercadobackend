package appmercadoback.proveedorComponent.controllers;


import appmercadoback.proveedorComponent.entitys.ProveedorEntity;
import appmercadoback.proveedorComponent.services.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> listarTodos() {
        return ResponseEntity.ok(proveedorService.listarProveedores());
    }

    @PostMapping
    public ResponseEntity<ProveedorEntity> crear(@RequestBody ProveedorEntity proveedor) {
        return ResponseEntity.ok(proveedorService.crearProveedor(proveedor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorEntity> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorEntity> actualizar(@PathVariable Integer id, @RequestBody ProveedorEntity proveedor) {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(id, proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}