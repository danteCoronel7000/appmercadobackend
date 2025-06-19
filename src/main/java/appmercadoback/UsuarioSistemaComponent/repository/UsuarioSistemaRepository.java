package appmercadoback.UsuarioSistemaComponent.repository;

import appmercadoback.UsuarioSistemaComponent.entitys.UsuarioSistemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistemaEntity, Long> {
    Optional<UsuarioSistemaEntity> findByName(String name);
}
