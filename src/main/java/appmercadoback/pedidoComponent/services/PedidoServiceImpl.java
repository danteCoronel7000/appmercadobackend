package appmercadoback.pedidoComponent.services;

import appmercadoback.pedidoComponent.entitys.PedidoEntity;
import appmercadoback.pedidoComponent.repositorys.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{
    private final PedidoRepository pedidoRepository;

    @Override
    public PedidoEntity guardar(PedidoEntity pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidoEntity> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<PedidoEntity> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public PedidoEntity actualizar(Long id, PedidoEntity pedidoActualizado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setFechaCreacion(pedidoActualizado.getFechaCreacion());
                    pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
                    pedido.setFechaSalida(pedidoActualizado.getFechaSalida());
                    pedido.setEstado(pedidoActualizado.getEstado());
                    pedido.setModoGeneracion(pedidoActualizado.getModoGeneracion());
                    pedido.setCliente(pedidoActualizado.getCliente());
                    pedido.setItems(pedidoActualizado.getItems());
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id " + id));
    }

    @Override
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
