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

    @Column(name = "referencia_direccion")
    private String referenciaDireccion;

    @Column(name = "frecuencia_compra")
    private String frecuenciaCompra;

    @Column(name = "dia_preferido_entrega")
    private String diaPreferidoDeEntrega; // 👈 Aquí está el nuevo campo

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id") // relación 1:1
    private PersonaEntity persona;
}
