package com.bookmyhotel.service;

import com.bookmyhotel.entity.Images;
import com.bookmyhotel.entity.PropertyUser;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

public interface ImageService {
    public Images uploadImage(MultipartFile file, String bucketName, long propertyId, PropertyUser propertyUser);

    void deleteImage(long id,String bucketName);
}
