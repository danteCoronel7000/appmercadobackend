package appmercadoback.itemCompraComponent.services;

import appmercadoback.itemCompraComponent.entitys.ItemCompraEntity;
import appmercadoback.itemCompraComponent.repositorys.ItemCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCompraServiceImpl implements ItemCompraService{

    private final ItemCompraRepository itemCompraRepository;

    @Override
    public ItemCompraEntity guardarItem(ItemCompraEntity itemCompra) {
        return itemCompraRepository.save(itemCompra);
    }

    @Override
    public List<ItemCompraEntity> listarItems() {
        return itemCompraRepository.findAll();
    }

    @Override
    public ItemCompraEntity obtenerPorId(Integer id) {
        Optional<ItemCompraEntity> item = itemCompraRepository.findById(id);
        return item.orElse(null);
    }

    @Override
    public void eliminarItem(Integer id) {
        itemCompraRepository.deleteById(id);
    }
}
