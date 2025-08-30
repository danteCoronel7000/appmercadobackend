package appmercadoback.categoriaComponent.services;

import appmercadoback.categoriaComponent.entitys.CategoriaEntity;
import appmercadoback.categoriaComponent.repository.CategoriaRepository;
import appmercadoback.productoComponent.entitys.Image;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;
    @Autowired
    private ImageService imageService;

    // Métodos para la gestión de categorías con imágenes en la nube (Cloudinary)
    @Override
    public CategoriaEntity saveCategoria(CategoriaEntity categoria, MultipartFile file, Integer parentId) throws IOException {
        // 👇 Validar si se proporcionó nombre u otro campo obligatorio
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la categoría es obligatorio");
        }

        if (parentId != null) {
            CategoriaEntity parent = categoriaRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada con id: " + parentId));
            categoria.setParent(parent);
        }

        // 👇 Si hay archivo, procesar la imagen
        if (file != null && !file.isEmpty()) {
            Image image = imageService.uploadImage(file);
            categoria.setImage(image);
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaEntity updateCategoriaAndImage(CategoriaEntity categoria, MultipartFile file, Integer parentId) throws IOException {
        // Obtener la categoría existente por su ID
        CategoriaEntity categoriaExistente = categoriaRepository.findById(categoria.getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + categoria.getId()));

        // 👇 Si llega una nueva imagen, eliminamos la anterior
        if (file != null && !file.isEmpty()) {
            if (categoriaExistente.getImage() != null) {
                imageService.deleteImage(categoriaExistente.getImage());
            }
            Image nuevaImagen = imageService.uploadImage(file);
            categoriaExistente.setImage(nuevaImagen);
        }

        if (parentId != null) {
            CategoriaEntity parent = categoriaRepository.findById(parentId)
                    .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada con id: " + parentId));
            categoriaExistente.setParent(parent);
        }

        // 👇 Actualizar los demás campos
        categoriaExistente.setNombre(categoria.getNombre());
        categoriaExistente.setDescripcion(categoria.getDescripcion());

        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public CategoriaEntity updateCategoria(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<CategoriaEntity> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<CategoriaEntity> getCategoriaById(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void deleteCategoria(CategoriaEntity categoria) throws IOException {
        if (categoria.getImage() != null) {
            imageService.deleteImage(categoria.getImage());
        }
        categoriaRepository.deleteById(categoria.getId());
    }

    @Override
    public CategoriaEntity updateCategoriaImage(MultipartFile file, CategoriaEntity categoria) throws IOException {
        if (categoria.getImage() != null) {
            imageService.deleteImage(categoria.getImage());
        }
        Image newImage = imageService.uploadImage(file);
        categoria.setImage(newImage);
        return categoriaRepository.save(categoria);
    }


    @Override
    public List<CategoriaEntity> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<CategoriaEntity> getCategoriasPrincipales() {
        return categoriaRepository.findCategoriasPrincipalesSeguro();
    }

    // En tu servicio
    @Override
    public Page<CategoriaEntity> getCategoriasPaginados(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }


}
