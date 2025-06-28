package appmercadoback.categoriaComponent.entitys;


import appmercadoback.productoComponent.entitys.Image;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProductoEntity> productos;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Image image;
}
