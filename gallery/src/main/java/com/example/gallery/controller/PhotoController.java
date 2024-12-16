package com.example.gallery.controller;

import com.example.gallery.entity.Photo;
import com.example.gallery.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    // Load the image upload page
    @GetMapping
    public String showUploadPage(Model model) {
        model.addAttribute("photos", photoService.getAllPhotos());
        return "upload";
    }

    // Handle image upload
    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            String name = file.getOriginalFilename();
            String type = file.getContentType();
            byte[] data = file.getBytes();

            System.out.println("File Name: " + name);
            System.out.println("File Size: " + data.length + " bytes");

            photoService.savePhoto(name, type, data);

            redirectAttributes.addFlashAttribute("message", "Image uploaded successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload image.");
        }

        return "redirect:/photos";
    }

    // Serve image data as a byte stream
    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> viewPhoto(@PathVariable Long id) {
        Photo photo = photoService.getPhotoById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(photo.getImageType()))
                .body(photo.getImageData());
    }
}



