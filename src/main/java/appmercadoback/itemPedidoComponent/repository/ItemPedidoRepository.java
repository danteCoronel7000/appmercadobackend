package appmercadoback.itemPedidoComponent.repository;

import appmercadoback.itemPedidoComponent.entitys.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {
}