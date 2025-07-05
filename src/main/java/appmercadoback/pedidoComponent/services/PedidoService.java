package appmercadoback.pedidoComponent.services;

import appmercadoback.pedidoComponent.entitys.PedidoEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    PedidoEntity guardar(PedidoEntity pedidoEntity);
    List<PedidoEntity> listarTodos();
    Optional<PedidoEntity> obtenerPorId(Long id);
    PedidoEntity actualizar(Long id, PedidoEntity pedidoEntity);
    void eliminar(Long id);
}
