package appmercadoback.UsuarioSistemaComponent.services;

import appmercadoback.UsuarioSistemaComponent.entitys.UsuarioSistemaEntity;

import java.util.List;

public interface UsuarioSistemaService {

    List<UsuarioSistemaEntity> listAllUsers();
    UsuarioSistemaEntity createUser(UsuarioSistemaEntity user);
    UsuarioSistemaEntity getUserById(Long id);
    UsuarioSistemaEntity updateUser(Long id, UsuarioSistemaEntity user);
    void deleteUser(Long id);
}
