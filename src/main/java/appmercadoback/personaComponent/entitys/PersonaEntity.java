package appmercadoback.personaComponent.entitys;
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
    private String direccion;
    private String telefono;
    private String genero;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Image imagen;
}