package appmercadoback.categoriaComponent.mappers;

import appmercadoback.categoriaComponent.DTOs.CategoriaDTO;
import appmercadoback.categoriaComponent.entitys.CategoriaEntity;

import java.util.List;

public class CategoriaMapper {

    public static CategoriaDTO toDTO(CategoriaEntity entity) {
        if (entity == null) return null;

        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setIsParent(entity.getIsParent());
        dto.setImage(entity.getImage());

        // Padre
        if (entity.getParent() != null) {
            dto.setParent(new CategoriaDTO.ParentDTO(
                    entity.getParent().getId(),
                    entity.getParent().getNombre()
            ));
        }

        // Subcategorías
        if (entity.getSubcategorias() != null) {
            List<CategoriaDTO.SubcategoriaDTO> subs = entity.getSubcategorias()
                    .stream()
                    .map(sub -> new CategoriaDTO.SubcategoriaDTO(sub.getId(), sub.getNombre()))
                    .toList();
            dto.setSubcategorias(subs);
        }

        return dto;
    }
}
