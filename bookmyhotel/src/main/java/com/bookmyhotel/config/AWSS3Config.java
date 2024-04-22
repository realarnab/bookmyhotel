package com.bookmyhotel.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config { //this class is responsible for accessing the AWS S3 bucket service
    @Value("${a}")
    private String accessKey;
    @Value("${s}")
    private String secretKey;
    @Value("${r}")
    private String region;

    public AWSCredentials credentials(){
        AWSCredentials credentials= new BasicAWSCredentials(accessKey,secretKey);
        return credentials;
    }

    @Bean
    public AmazonS3 amazonS3(){
        AmazonS3 s3client= AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(region).build();
        return s3client;
    }

}
