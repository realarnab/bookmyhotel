package com.bookmyhotel.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BucketService {
    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file, String bucketName){ //this method is used for upload the file in the S3 bucket
        if (file.isEmpty()){ //its checked that file is not empty
            throw new IllegalStateException("'can not upload empty file");
        }
        try {
            File convFile=new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            try{
                amazonS3.putObject(bucketName,convFile.getName(),convFile);
                return amazonS3.getUrl(bucketName,file.getOriginalFilename()).toString();
            }catch (AmazonS3Exception e){
                return "unable to upload the file "+e.getMessage();
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to upload file "+e);
        }
    }
}
