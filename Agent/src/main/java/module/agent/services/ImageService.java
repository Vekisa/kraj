package module.agent.services;

import module.agent.model.Image;
import module.agent.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public Image create(Image image){
        return imageRepository.save(image);
    }
}
