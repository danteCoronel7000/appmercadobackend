package appmercadoback.productoComponent.entitys;


import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.proveedorComponent.entitys.ProveedorEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private String medida;
    private Integer stockActual;
    private Integer stockMin;

    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    private Integer popularidad;

    private String etiquetas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties({"productos"})
    private CategoriaEntity categoria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "producto_proveedor",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "proveedor_id")
    )
    @JsonIgnoreProperties({"productos"}) // Si ProveedorEntity también tiene referencia a productos
    private List<ProveedorEntity> proveedores;

}