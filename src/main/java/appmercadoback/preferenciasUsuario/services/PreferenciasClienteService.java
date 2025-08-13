package appmercadoback.preferenciasUsuario.services;

import appmercadoback.productoComponent.entitys.ProductoEntity;

import java.util.List;

public interface PreferenciasClienteService {
    /**
     * Obtiene una lista de productos recomendados para un cliente,
     * en base a sus interacciones recientes (visitas, búsquedas, likes).
     *
     * @param clienteId ID del cliente para el que se generan las recomendaciones.
     * @return Lista de productos recomendados.
     */
    List<ProductoEntity> obtenerRecomendaciones(Long clienteId);
}
