package appmercadoback.clienteComponent.repositorys;

import appmercadoback.clienteComponent.entitys.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByCorreo(String correo);
}
