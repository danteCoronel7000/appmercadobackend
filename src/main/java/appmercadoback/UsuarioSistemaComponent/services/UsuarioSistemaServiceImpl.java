package appmercadoback.UsuarioSistemaComponent.services;


import appmercadoback.UsuarioSistemaComponent.entitys.UsuarioSistemaEntity;
import appmercadoback.UsuarioSistemaComponent.repository.UsuarioSistemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioSistemaServiceImpl implements UsuarioSistemaService {

    private final UsuarioSistemaRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioSistemaEntity> listAllUsers() {
        return usuarioRepo.findAll();
    }

    @Override
    public UsuarioSistemaEntity createUser(UsuarioSistemaEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepo.save(user);
    }

    @Override
    public UsuarioSistemaEntity getUserById(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public UsuarioSistemaEntity updateUser(Long id, UsuarioSistemaEntity user) {
        UsuarioSistemaEntity existing = getUserById(id);
        existing.setName(user.getName());
        existing.setPassword(user.getPassword());
        existing.setEstado(user.getEstado());
        existing.setPersona(user.getPersona());
        return usuarioRepo.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        usuarioRepo.deleteById(id);
    }
}
