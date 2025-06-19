package appmercadoback.OrdenCompraComponent.repositorys;

import appmercadoback.OrdenCompraComponent.entitys.OrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity, Integer> {
}
