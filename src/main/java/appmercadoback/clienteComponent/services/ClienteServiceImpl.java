package appmercadoback.clienteComponent.services;


import appmercadoback.clienteComponent.entitys.ClienteEntity;
import appmercadoback.clienteComponent.repositorys.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteEntity guardar(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<ClienteEntity> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<ClienteEntity> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public ClienteEntity actualizar(Long id, ClienteEntity actualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setReferenciaDireccion(actualizado.getReferenciaDireccion());
                    cliente.setFrecuenciaCompra(actualizado.getFrecuenciaCompra());
                    cliente.setDiaPreferidoDeEntrega(actualizado.getDiaPreferidoDeEntrega());
                    cliente.setPersona(actualizado.getPersona());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
