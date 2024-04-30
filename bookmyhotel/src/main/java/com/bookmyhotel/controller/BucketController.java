package com.bookmyhotel.controller;

import com.bookmyhotel.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3bucket")
public class BucketController {
    @Autowired
    BucketService service;

    @PostMapping(path="/upload/file/{bucketName}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file, @PathVariable String bucketName){
        return new ResponseEntity<>(service.uploadFile(file,bucketName), HttpStatus.OK);
    }

    @DeleteMapping("/delete/file/{bucketName}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String bucketName, @PathVariable String fileName) throws IOException {
        String message= service.deleteFile(bucketName, fileName);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
