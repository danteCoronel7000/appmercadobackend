package appmercadoback.itemPedidoComponent.services;

import appmercadoback.itemPedidoComponent.entitys.ItemPedidoEntity;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoService {
    ItemPedidoEntity guardar(ItemPedidoEntity item);
    List<ItemPedidoEntity> listarTodos();
    Optional<ItemPedidoEntity> obtenerPorId(Long id);
    ItemPedidoEntity actualizar(Long id, ItemPedidoEntity itemActualizado);
    void eliminar(Long id);
}
