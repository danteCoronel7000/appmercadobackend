package appmercadoback.OrdenCompraComponent.services;

import appmercadoback.OrdenCompraComponent.entitys.OrdenCompraEntity;
import appmercadoback.OrdenCompraComponent.repositorys.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService{

    private final OrdenCompraRepository ordenCompraRepository;

    @Autowired
    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    @Override
    public List<OrdenCompraEntity> obtenerTodas() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public Optional<OrdenCompraEntity> obtenerPorId(Integer id) {
        return ordenCompraRepository.findById(id);
    }

    @Override
    public OrdenCompraEntity crear(OrdenCompraEntity ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @Override
    public OrdenCompraEntity actualizar(Integer id, OrdenCompraEntity ordenActualizada) {
        return ordenCompraRepository.findById(id).map(ordenExistente -> {
            ordenExistente.setFechaOrden(ordenActualizada.getFechaOrden());
            ordenExistente.setEstado(ordenActualizada.getEstado());
            ordenExistente.setProveedor(ordenActualizada.getProveedor());
            ordenExistente.setItems(ordenActualizada.getItems());
            ordenExistente.setTotal(ordenActualizada.getTotal());
            return ordenCompraRepository.save(ordenExistente);
        }).orElseThrow(() -> new RuntimeException("Orden de compra no encontrada con ID: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        ordenCompraRepository.deleteById(id);
    }
}
