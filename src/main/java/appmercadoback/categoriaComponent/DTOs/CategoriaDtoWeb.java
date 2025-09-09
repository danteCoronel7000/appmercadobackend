package appmercadoback.categoriaComponent.DTOs;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;

public class CategoriaDtoWeb {
    private Integer id;
    private String nombre;

    public CategoriaDtoWeb(CategoriaEntity categoria){
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
    }
}
