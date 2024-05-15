package com.bookmyhotel.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

//    public String deleteFile(String bucketName, String fileName) throws IOException {
//        try{
//            amazonS3.deleteObject(bucketName, fileName);
//            return "Successfully Deleted";
//        } catch (AmazonS3Exception e) {
//            throw new IllegalStateException("error!! " + e.getMessage(), e);
//        }
//    }
    public void deleteFile(String fileUrl,String bucketName) {
        String key = getKeyFromUrl(fileUrl);
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
    }

    private String getKeyFromUrl(String fileUrl) {
        // Extract key from S3 URL
        return fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    }
}
