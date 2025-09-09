package appmercadoback.unidadMedidaComponent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidad_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 20)
    private String abreviatura;
}
