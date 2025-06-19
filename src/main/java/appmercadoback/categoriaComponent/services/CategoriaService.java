package appmercadoback.categoriaComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    CategoriaEntity guardarCategoria(CategoriaEntity categoria);

    List<CategoriaEntity> obtenerTodas();

    Optional<CategoriaEntity> obtenerPorId(Integer id);

    CategoriaEntity actualizarCategoria(Integer id, CategoriaEntity categoriaActualizada);

    CategoriaEntity eliminarCategoriaYRetornar(Integer id);

    void eliminarCategoria(Integer id);

    List<CategoriaEntity> buscarPorNombre(String nombre);
}
