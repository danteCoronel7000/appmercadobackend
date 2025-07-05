package appmercadoback.itemPedidoComponent.services;

import appmercadoback.itemPedidoComponent.entitys.ItemPedidoEntity;
import appmercadoback.itemPedidoComponent.repository.ItemPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public ItemPedidoEntity guardar(ItemPedidoEntity item) {
        return itemPedidoRepository.save(item);
    }

    @Override
    public List<ItemPedidoEntity> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    @Override
    public Optional<ItemPedidoEntity> obtenerPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    @Override
    public ItemPedidoEntity actualizar(Long id, ItemPedidoEntity actualizado) {
        return itemPedidoRepository.findById(id)
                .map(item -> {
                    item.setCantidad(actualizado.getCantidad());
                    item.setPrecioUnitario(actualizado.getPrecioUnitario());
                    item.setPedido(actualizado.getPedido());
                    item.setProducto(actualizado.getProducto());
                    return itemPedidoRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("ItemPedido no encontrado con id " + id));
    }

    @Override
    public void eliminar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
