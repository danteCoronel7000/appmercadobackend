package appmercadoback.personaComponent.repository;

import appmercadoback.personaComponent.entitys.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
