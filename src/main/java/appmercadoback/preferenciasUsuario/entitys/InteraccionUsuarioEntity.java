package appmercadoback.preferenciasUsuario.entitys;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.clienteComponent.entitys.ClienteEntity;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interaccion_usuario")
public class InteraccionUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @Column(nullable = false)
    private String tipo; // VISITA, BUSQUEDA, LIKE

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    // Getters y setters
}

