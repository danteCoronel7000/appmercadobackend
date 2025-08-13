package appmercadoback.preferenciasUsuario.services;

import appmercadoback.preferenciasUsuario.repository.InteraccionUsuarioRepository;
import appmercadoback.productoComponent.entitys.ProductoEntity;
import appmercadoback.productoComponent.repositorys.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PreferenciasClienteServiceImpl implements PreferenciasClienteService{

    @Autowired
    private InteraccionUsuarioRepository interaccionRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public List<ProductoEntity> obtenerRecomendaciones(Long clienteId) {
        LocalDateTime hace30Dias = LocalDateTime.now().minusDays(30);

        // 1. Obtener top categorías usando consulta nativa
        List<Long> topCategorias = interaccionRepo
                .findTopCategoriasByClienteNative(clienteId, hace30Dias)
                .stream()
                .map(row -> ((Number) row[0]).longValue()) // catId está en la columna 0
                .toList();

        if (topCategorias.isEmpty()) {
            return List.of();
        }

        // 2. Obtener productos recomendados usando consulta nativa
        return productoRepo.findRecomendadosByCategoriasNative(
                topCategorias,
                clienteId,
                20 // Límite de productos recomendados
        );
    }
}
