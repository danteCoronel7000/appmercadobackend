package appmercadoback.UsuarioSistemaComponent.services;


import appmercadoback.UsuarioSistemaComponent.entitys.UsuarioSistemaEntity;
import appmercadoback.UsuarioSistemaComponent.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioSistemaRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSistemaEntity userModel = this.iUserRepository
                .findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(
                userModel.getName(),
                userModel.getPassword(),
                new ArrayList<>()
        );
    }
}
