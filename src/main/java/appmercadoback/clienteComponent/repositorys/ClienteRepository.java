package appmercadoback.clienteComponent.repositorys;

import appmercadoback.clienteComponent.entitys.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    /*
    SELECT c.*
Qué hace: Selecciona todas las columnas de la tabla cliente (alias c).
Dudita sana: ¿Por qué *? Funciona, pero en producción suele ser mejor enumerar columnas para evitar traer basura y romper mapeos si agregas campos.
FROM cliente c
Qué hace: Define la tabla base y el alias c (para escribir menos, vivir más).
Nota: Debe coincidir con el nombre real de la tabla en tu DB.
INNER JOIN persona p ON c.persona_id = p.id
Qué hace: Une cliente con persona por la FK persona_id.
Por qué: Como ahora el correo está en persona, necesitas el join.
Semántica: INNER exige que exista la persona; si no hay persona, no hay cliente devuelto (consistencia ante todo).
WHERE p.gmail = :correo
Qué hace: Filtra por el correo (que vive en persona.gmail).
:correo: Parámetro nombrado; aún no tiene valor hasta que Spring lo enlace con @Param("correo").
Pro-tip: Asegura índice/unique en persona.gmail si esperas unicidad; evita sorpresas de “tengo dos Juan Pérez con el mismo mail”.
     */
    //obtener cliente por correo consulta nativa
    @Query(value = """
    SELECT c.* 
    FROM cliente c
    INNER JOIN persona p ON c.persona_id = p.id
    WHERE p.gmail = :correo
    """, nativeQuery = true)
    ClienteEntity obtenerClientePorCorreo(@Param("correo") String correo);

}
