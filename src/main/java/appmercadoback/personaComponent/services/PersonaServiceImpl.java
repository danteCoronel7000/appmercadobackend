package appmercadoback.personaComponent.services;


import appmercadoback.personaComponent.entitys.PersonaEntity;
import appmercadoback.personaComponent.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Override
    public List<PersonaEntity> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public PersonaEntity createPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity getPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona not found with id " + id));
    }

    @Override
    public PersonaEntity updatePersona(Long id, PersonaEntity persona) {
        PersonaEntity existing = getPersonaById(id);
        existing.setNombre(persona.getNombre());
        existing.setApellidoPaterno(persona.getApellidoPaterno());
        existing.setApellidoMaterno(persona.getApellidoMaterno());
        existing.setDireccion(persona.getDireccion());
        existing.setTelefono(persona.getTelefono());
        existing.setGenero(persona.getGenero());
        existing.setImagen(persona.getImagen());
        return personaRepository.save(existing);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
}