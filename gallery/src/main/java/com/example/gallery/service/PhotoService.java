package com.example.gallery.service;

import com.example.gallery.entity.Photo;
import com.example.gallery.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo savePhoto(String name, String type, byte[] data) {
        Photo photo = new Photo();
        photo.setImageName(name);
        photo.setImageType(type);
        photo.setImageData(data);
        return photoRepository.save(photo);
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found with id: " + id));
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
