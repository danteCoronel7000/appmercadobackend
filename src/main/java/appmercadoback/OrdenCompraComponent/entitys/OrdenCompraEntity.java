package appmercadoback.OrdenCompraComponent.entitys;


import appmercadoback.itemCompraComponent.entitys.ItemCompraEntity;
import appmercadoback.proveedorComponent.entitys.ProveedorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrden;

    private Float total;

    @Enumerated(EnumType.STRING)
    private EstadoOrden estado;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private ProveedorEntity proveedor;

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompraEntity> items;

    public enum EstadoOrden {
        PENDIENTE, ENTREGADA, CANCELADA
    }
}
