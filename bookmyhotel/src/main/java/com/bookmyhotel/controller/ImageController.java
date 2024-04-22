package com.bookmyhotel.controller;

import com.bookmyhotel.entity.Images;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.exceptions.PropertyNotFound;
import com.bookmyhotel.repository.ImagesRepository;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private PropertyRepository propertyRepository;;

    @PostMapping(path="/upload/{bucketName}/property/{propertyId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @PathVariable String bucketName, @PathVariable long propertyId, @AuthenticationPrincipal PropertyUser propertyUser){
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFound("Property not found with id: " + propertyId));
        Images images=new Images();
        images.setImageUrl(imageUrl);
        images.setProperty(property);
        images.setPropertyUser(propertyUser);
        Images savedImage = imagesRepository.save(images);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }
}
