package appmercadoback.UsuarioSistemaComponent.controllers;

import appmercadoback.UsuarioSistemaComponent.entitys.UsuarioSistemaEntity;
import appmercadoback.UsuarioSistemaComponent.services.UsuarioSistemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system-users")
@RequiredArgsConstructor
public class UsuarioSistemaController {

    private final UsuarioSistemaService userService;

    @GetMapping
    public ResponseEntity<List<UsuarioSistemaEntity>> listAll() {
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @PostMapping
    public ResponseEntity<UsuarioSistemaEntity> create(@RequestBody UsuarioSistemaEntity user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSistemaEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioSistemaEntity> update(@PathVariable Long id, @RequestBody UsuarioSistemaEntity user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}