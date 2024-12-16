package com.example.gallery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photo_gallery")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name= "imagedata")
    private byte[] imageData;

    @Column(name = "imagename")
    private  String imageName;

    @Column(name = "imagetype")
    private  String imageType;


}
