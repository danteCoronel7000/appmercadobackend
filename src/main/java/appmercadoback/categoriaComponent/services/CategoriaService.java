package appmercadoback.categoriaComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    /*
    CategoriaEntity guardarCategoria(CategoriaEntity categoria);

    List<CategoriaEntity> obtenerTodas();

    Optional<CategoriaEntity> obtenerPorId(Integer id);

    CategoriaEntity actualizarCategoria(Integer id, CategoriaEntity categoriaActualizada);

    CategoriaEntity eliminarCategoriaYRetornar(Integer id);

    void eliminarCategoria(Integer id);
     */
    //metodos de la interfaza para getionar categorias con imagenes en cloudInary
    CategoriaEntity saveCategoria(CategoriaEntity categoria, MultipartFile file, Integer parentId) throws IOException;

    CategoriaEntity updateCategoriaAndImage(CategoriaEntity categoria, MultipartFile file, Integer parentId) throws IOException;

    CategoriaEntity updateCategoria(CategoriaEntity categoria);

    List<CategoriaEntity> getCategorias();

    Optional<CategoriaEntity> getCategoriaById(Integer id);

    void deleteCategoria(CategoriaEntity categoria) throws IOException;

    CategoriaEntity updateCategoriaImage(MultipartFile file, CategoriaEntity categoria) throws IOException;


    List<CategoriaEntity> buscarPorNombre(String nombre);

    List<CategoriaEntity> getCategoriasPrincipales();

    Page<CategoriaEntity> getCategoriasPaginados(Pageable pageable);
}
