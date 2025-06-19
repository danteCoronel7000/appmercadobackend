package appmercadoback.productoComponent.repositorys;

import appmercadoback.productoComponent.entitys.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
