package appmercadoback.clienteComponent.entitys;

import appmercadoback.personaComponent.entitys.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correo;
    private String telefono;

    @Column(name = "referencia_direccion")
    private String referenciaDireccion;

    @Column(name = "frecuencia_compra")
    private String frecuenciaCompra;

    @Column(name = "dia_preferido_entrega")
    private String diaPreferidoDeEntrega; // 👈 Aquí está el nuevo campo

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private PersonaEntity persona;
}
