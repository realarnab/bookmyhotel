package com.bookmyhotel.service.impl;

import com.bookmyhotel.entity.Images;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.exceptions.ImageNotFoundException;
import com.bookmyhotel.exceptions.PropertyNotFound;
import com.bookmyhotel.repository.ImagesRepository;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.service.BucketService;
import com.bookmyhotel.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Images uploadImage(MultipartFile file, String bucketName, long propertyId, PropertyUser propertyUser) {
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFound("Property not found with id: " + propertyId));
        Images images=new Images();
        images.setImageUrl(imageUrl);
        images.setProperty(property);
        images.setPropertyUser(propertyUser);
        Images savedImage = imagesRepository.save(images);
        return savedImage;
    }

    @Override
    @Transactional
    public void deleteImage(long id,String bucketName) {
        Images image = imagesRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("No Image is found with id: " + id));
        bucketService.deleteFile(image.getImageUrl(),bucketName);
        imagesRepository.deleteById(id);
    }
}
