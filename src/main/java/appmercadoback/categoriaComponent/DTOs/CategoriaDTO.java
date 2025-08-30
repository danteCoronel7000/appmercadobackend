package appmercadoback.categoriaComponent.DTOs;

import appmercadoback.productoComponent.entitys.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer isParent;
    private Image image;

    private ParentDTO parent; // 👈 Aquí guardamos solo lo necesario del padre
    private List<SubcategoriaDTO> subcategorias; // 👈 Si querés mostrar hijos también

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParentDTO {
        private Integer id;
        private String nombre;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubcategoriaDTO {
        private Integer id;
        private String nombre;
    }
}
