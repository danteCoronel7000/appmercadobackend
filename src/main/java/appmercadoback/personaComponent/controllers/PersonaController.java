package appmercadoback.personaComponent.controllers;


import appmercadoback.personaComponent.entitys.PersonaEntity;
import appmercadoback.personaComponent.services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaEntity>> listAll() {
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    @PostMapping
    public ResponseEntity<PersonaEntity> create(@RequestBody PersonaEntity persona) {
        return ResponseEntity.ok(personaService.createPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.getPersonaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaEntity> update(@PathVariable Long id, @RequestBody PersonaEntity persona) {
        return ResponseEntity.ok(personaService.updatePersona(id, persona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}