package appmercadoback.clienteComponent.services;

import appmercadoback.clienteComponent.entitys.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteEntity guardar(ClienteEntity cliente);
    List<ClienteEntity> listarTodos();
    Optional<ClienteEntity> obtenerPorId(Long id);
    ClienteEntity actualizar(Long id, ClienteEntity cliente);
    void eliminar(Long id);
}
