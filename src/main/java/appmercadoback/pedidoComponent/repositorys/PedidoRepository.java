package appmercadoback.pedidoComponent.repositorys;

import appmercadoback.pedidoComponent.entitys.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
