package com.thesis.backend.services;

import com.thesis.backend.entities.Image;
import com.thesis.backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image store(MultipartFile file) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(filename, file.getContentType(), file.getBytes());
        return imageRepository.save(image);
    }

    public Image getImage(int id){
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllImages(){
        return imageRepository.findAll().stream();
    }
}
