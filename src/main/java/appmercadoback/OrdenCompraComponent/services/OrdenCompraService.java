package appmercadoback.OrdenCompraComponent.services;

import appmercadoback.OrdenCompraComponent.entitys.OrdenCompraEntity;

import java.util.List;
import java.util.Optional;

public interface OrdenCompraService {

    List<OrdenCompraEntity> obtenerTodas();

    Optional<OrdenCompraEntity> obtenerPorId(Integer id);

    OrdenCompraEntity crear(OrdenCompraEntity ordenCompra);

    OrdenCompraEntity actualizar(Integer id, OrdenCompraEntity ordenActualizada);

    void eliminar(Integer id);
}
