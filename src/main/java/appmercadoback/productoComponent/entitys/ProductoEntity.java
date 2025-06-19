package appmercadoback.productoComponent.entitys;


import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.proveedorComponent.entitys.ProveedorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Float precio;
    private Boolean perecedero;
    private String unidadMedida;
    private Integer stockActual;
    private Integer stockMin;

    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    private Integer popularidad;

    private String etiquetas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private ProveedorEntity proveedor;
}