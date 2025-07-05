package appmercadoback.clienteComponent.repositorys;

import appmercadoback.clienteComponent.entitys.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
