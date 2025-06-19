package appmercadoback.productoComponent.services;

import appmercadoback.productoComponent.entitys.ImageEntity;
import appmercadoback.productoComponent.repositorys.ImageRepository;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageEntity uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinaryService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        String imageId = (String) uploadResult.get("public_id");
        ImageEntity image = new ImageEntity(file.getOriginalFilename(), imageUrl, imageId);
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(ImageEntity image) throws IOException {
        cloudinaryService.delete(image.getImageId());
        imageRepository.deleteById(image.getId());
    }


}
