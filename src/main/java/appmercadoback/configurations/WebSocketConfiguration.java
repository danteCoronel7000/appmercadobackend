package appmercadoback.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
//activamos todo lo necesario para usar STOMP/WebSocket

public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    // Esta parte configura el "broker" de mensajes (como si fuera una radio de canales)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // Lo anterior permite a los clientes suscribirse a "/topic/..."
        config.setApplicationDestinationPrefixes("/app");
        //Todo mensaje enviado desde el cliente con destino "/app/..." se enruta al servidor
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // permite peticiones desde cualquier origen
                .withSockJS(); // Habilita SockJS para navegadores que no soportan WebSocket
    }
}
