package appmercadoback.categoriaComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.categoriaComponent.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaEntity guardarCategoria(CategoriaEntity categoria) {
        System.out.println("⏳ Guardando categoría: " + categoria.getNombre());
        Arrays.stream(Thread.currentThread().getStackTrace())
                .forEach(s -> System.out.println("  at " + s));

        categoria.setProductos(new ArrayList<>()); // o new ArrayList<>()
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<CategoriaEntity> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<CategoriaEntity> obtenerPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public CategoriaEntity actualizarCategoria(Integer id, CategoriaEntity categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaActualizada.getNombre());
                    categoria.setDescripcion(categoriaActualizada.getDescripcion());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    public CategoriaEntity eliminarCategoriaYRetornar(Integer id) {
        // Primero buscamos la categoría
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        // Eliminamos la categoría
        categoriaRepository.delete(categoria);

        // Retornamos la categoría eliminada
        return categoria;
    }

    public void eliminarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public List<CategoriaEntity> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }


}
