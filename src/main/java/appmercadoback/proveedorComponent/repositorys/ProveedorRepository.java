package appmercadoback.proveedorComponent.repositorys;

import appmercadoback.proveedorComponent.entitys.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
}
