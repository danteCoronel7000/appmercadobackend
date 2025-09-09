package appmercadoback.ConversionProductoComponent.Entity;

import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.unidadMedidaComponent.entity.UnidadMedidaEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "conversion_producto",
        uniqueConstraints = @UniqueConstraint(columnNames = {"producto_id", "unidad_compra_id", "unidad_venta_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // FK a ProductoEntity (usa la clase que ya tenés)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnoreProperties({"proveedores", "categoria"}) // evita ciclos; ajustá según tu ProductoEntity
    private ProductoEntity producto;

    // Unidad usada para la compra
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "unidad_compra_id", nullable = false)
    private UnidadMedidaEntity unidadCompra;

    // Unidad usada para la venta
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "unidad_venta_id", nullable = false)
    private UnidadMedidaEntity unidadVenta;

    // factor: cuántas unidades de venta equivalen a 1 unidad de compra.
    @Column(precision = 19, scale = 6, nullable = false)
    private BigDecimal factor;
}
