package appmercadoback.preferenciasUsuario.repository;

import appmercadoback.preferenciasUsuario.entitys.InteraccionUsuarioEntity;
import appmercadoback.preferenciasUsuario.proyecciones.CategoriaPuntuacion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InteraccionUsuarioRepository extends JpaRepository<InteraccionUsuarioEntity, Long> {

    @Query(
            value = """
            SELECT 
                COALESCE(i.categoria_id, p.categoria_id) AS catId,
                SUM(
                    CASE 
                        WHEN i.tipo = 'VISITA' THEN 1
                        WHEN i.tipo = 'BUSQUEDA' THEN 2
                        WHEN i.tipo = 'LIKE' THEN 5
                        ELSE 0
                    END
                ) AS puntuacion
            FROM interaccion_usuario i
            LEFT JOIN producto p ON i.producto_id = p.id
            WHERE i.cliente_id = :clienteId
              AND i.fecha > :desde
            GROUP BY COALESCE(i.categoria_id, p.categoria_id)
            ORDER BY puntuacion DESC
            LIMIT 3
        """,
            nativeQuery = true
    )
    List<Object[]> findTopCategoriasByClienteNative(
            @Param("clienteId") Long clienteId,
            @Param("desde") LocalDateTime desde
    );

}
