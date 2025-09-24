package appmercadoback.categoriaComponent.DTOs;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDtoWeb {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imgUrl;
    private List<SubCategoriaDto> subCategorias;

    public CategoriaDtoWeb(CategoriaEntity categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
        this.descripcion = categoria.getDescripcion();
        this.imgUrl = categoria.getImage() != null ? categoria.getImage().getImageUrl() : null;
        // Solo incluir subcategorías si existen y hay más de 2
        if (categoria.getSubcategorias() != null && !categoria.getSubcategorias().isEmpty()) {
            this.subCategorias = categoria.getSubcategorias().stream()
                    .map(SubCategoriaDto::new)
                    .collect(Collectors.toList());
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubCategoriaDto {
        private Integer id;
        private String nombre;
        private String descripcion;
        private String imgUrl;

        public SubCategoriaDto(CategoriaEntity categoria) {
            this.id = categoria.getId();
            this.nombre = categoria.getNombre();
            this.descripcion = categoria.getDescripcion();
            this.imgUrl = categoria.getImage() != null ? categoria.getImage().getImageUrl() : null;
        }
    }
}