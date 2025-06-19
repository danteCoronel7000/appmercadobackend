package appmercadoback.personaComponent.services;

import appmercadoback.personaComponent.entitys.PersonaEntity;

import java.util.List;

public interface PersonaService {

    List<PersonaEntity> getAllPersonas();
    PersonaEntity createPersona(PersonaEntity persona);
    PersonaEntity getPersonaById(Long id);
    PersonaEntity updatePersona(Long id, PersonaEntity persona);
    void deletePersona(Long id);
}
