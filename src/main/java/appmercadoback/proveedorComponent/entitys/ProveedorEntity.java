package appmercadoback.proveedorComponent.entitys;


import appmercadoback.personaComponent.entitys.PersonaEntity;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private PersonaEntity persona;

    @ManyToMany(mappedBy = "proveedores")
    @JsonBackReference
    private List<ProductoEntity> productos;
}