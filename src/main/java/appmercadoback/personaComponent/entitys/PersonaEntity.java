package appmercadoback.personaComponent.entitys;
import appmercadoback.clienteComponent.entitys.ClienteEntity;
import appmercadoback.productoComponent.entitys.Image;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String gmail;
    private String direccion;
    private String telefono;
    private String genero;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private ClienteEntity cliente;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Image imagen;
}