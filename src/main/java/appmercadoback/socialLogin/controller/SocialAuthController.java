package appmercadoback.socialLogin.controller;


import appmercadoback.UsuarioSistemaComponent.dto.AuthResponseDto;
import appmercadoback.UsuarioSistemaComponent.entitys.UsuarioSistemaEntity;
import appmercadoback.UsuarioSistemaComponent.repository.UsuarioSistemaRepository;
import appmercadoback.UsuarioSistemaComponent.services.JwtUtilService;
import appmercadoback.clienteComponent.entitys.ClienteEntity;
import appmercadoback.personaComponent.entitys.PersonaEntity;
import appmercadoback.personaComponent.repository.PersonaRepository;
import appmercadoback.socialLogin.dtOs.TokenDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/get/social-login")
public class SocialAuthController {
    @Value("${google.clientId}")
    String googleClientId;
    @Autowired
    UsuarioSistemaRepository userRepository;
    @Autowired
    JwtUtilService jwtUtilService;
    @Autowired
    PersonaRepository personaRepository;

    @PostMapping("/google")
    public ResponseEntity<?> googleAuth(@RequestBody TokenDto tokenDto) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));

        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();

        String email = payload.getEmail();
        String nombre = (String) payload.get("given_name");
        String apellido = (String) payload.get("family_name");

        // Buscar usuario por email (en persona.gmail)
        UsuarioSistemaEntity usuario = userRepository.findByPersona_Gmail(email).orElse(null);

        if (usuario == null) {
            // Crear persona
            PersonaEntity persona = PersonaEntity.builder()
                    .nombre(nombre)
                    .apellidoPaterno(apellido)
                    .gmail(email)
                    .direccion(null)
                    .telefono(null)
                    .genero(null)
                    .imagen(null)
                    .build();

            // Crear cliente asociado a la persona
            ClienteEntity cliente = ClienteEntity.builder()
                    .referenciaDireccion(null)
                    .frecuenciaCompra(null)
                    .diaPreferidoDeEntrega(null)
                    .persona(persona)
                    .build();

            persona.setCliente(cliente); // relación bidireccional

            // Crear usuario asociado
            usuario = UsuarioSistemaEntity.builder()
                    .name(email)
                    .password(null)
                    .estado("ACTIVO")
                    .role("USER")
                    .persona(persona)
                    .build();

            userRepository.save(usuario);
            // 👉 gracias al cascade en Persona → Cliente, también guarda cliente
        }


        // Generar UserDetails
        String role = usuario.getRole();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usuario.getName(),
                usuario.getPassword() != null ? usuario.getPassword() : "",
                List.of(new SimpleGrantedAuthority(role))
        );

        // Generar tokens
        String jwt = jwtUtilService.generateToken(userDetails, role);
        String refreshToken = jwtUtilService.generateRefreshToken(userDetails, role);

        // Respuesta
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwt);
        authResponseDto.setRefreshToken(refreshToken);

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }


    @PostMapping("/facebook")
    public ResponseEntity<?> facebookAuth(@RequestBody TokenDto tokenDto){
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String []  fields = {"email", "picture"};
        User user = facebook. fetchObject("me", User.class, fields);
        return new
                ResponseEntity(user, HttpStatus.OK);
    }
}
