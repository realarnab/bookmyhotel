package com.bookmyhotel.controller;

import com.bookmyhotel.entity.Images;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.exceptions.PropertyNotFound;
import com.bookmyhotel.repository.ImagesRepository;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.service.BucketService;
import com.bookmyhotel.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private PropertyRepository propertyRepository;;

    @PostMapping(path="/upload/{bucketName}/property/{propertyId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @PathVariable String bucketName, @PathVariable long propertyId, @AuthenticationPrincipal PropertyUser propertyUser){
        Images saved = imageService.uploadImage(file, bucketName, propertyId, propertyUser);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

}
