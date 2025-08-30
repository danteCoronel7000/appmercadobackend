package appmercadoback.categoriaComponent.entitys;
import appmercadoback.productoComponent.entitys.Image;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer isParent;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonBackReference("categoria-productos")
    private List<ProductoEntity> productos;

    // PADRE - NO se serializa (evita bucle infinito)
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference("parent-subcategorias") // ← CAMBIADO: BackReference aquí
    private CategoriaEntity parent;

    // SUBCATEGORÍAS - SE serializa
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference("parent-subcategorias") // ← CAMBIADO: ManagedReference aquí
    private List<CategoriaEntity> subcategorias;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Image image;
}