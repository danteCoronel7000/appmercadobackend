package appmercadoback.itemCompraComponent.services;

import appmercadoback.itemCompraComponent.entitys.ItemCompraEntity;

import java.util.List;

public interface ItemCompraService {
    ItemCompraEntity guardarItem(ItemCompraEntity itemCompra);

    List<ItemCompraEntity> listarItems();

    ItemCompraEntity obtenerPorId(Integer id);

    void eliminarItem(Integer id);
}
