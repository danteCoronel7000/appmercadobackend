package appmercadoback.productoComponent.services;

import appmercadoback.productoComponent.entitys.ImageEntity;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public interface ImageService {
    ImageEntity uploadImage(MultipartFile file) throws IOException;
    void deleteImage(ImageEntity image) throws IOException;
}
