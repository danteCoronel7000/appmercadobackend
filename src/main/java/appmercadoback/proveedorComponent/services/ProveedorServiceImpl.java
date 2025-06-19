package appmercadoback.proveedorComponent.services;

import appmercadoback.proveedorComponent.entitys.ProveedorEntity;
import appmercadoback.proveedorComponent.repositorys.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorEntity> listarProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public ProveedorEntity crearProveedor(ProveedorEntity proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public ProveedorEntity obtenerPorId(Integer id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @Override
    public ProveedorEntity actualizarProveedor(Integer id, ProveedorEntity proveedor) {
        ProveedorEntity existente = obtenerPorId(id);
        existente.setPersona(proveedor.getPersona());
        return proveedorRepository.save(existente);
    }

    @Override
    public void eliminarProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }
}