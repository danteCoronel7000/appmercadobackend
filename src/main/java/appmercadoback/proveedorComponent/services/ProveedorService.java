package appmercadoback.proveedorComponent.services;

import appmercadoback.proveedorComponent.entitys.ProveedorEntity;

import java.util.List;

public interface ProveedorService {

    List<ProveedorEntity> listarProveedores();
    ProveedorEntity crearProveedor(ProveedorEntity proveedor);
    ProveedorEntity obtenerPorId(Integer id);
    ProveedorEntity actualizarProveedor(Integer id, ProveedorEntity proveedor);
    void eliminarProveedor(Integer id);
}
