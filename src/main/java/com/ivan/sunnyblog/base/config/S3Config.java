package com.ivan.sunnyblog.base.config;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Author: jinghaoliang
 **/
@Component
public class S3Config {

//    @Value("${aws.credentials.access-key}")
//    private String accessKey;
//
//    @Value(("${aws.credentials.secret-key}"))
//    private String secretKey;

    @Value("${aws.region.static}")
    private String region;


    @Bean
    public AmazonS3 s3Client(){
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withCredentials()
                .withRegion(region)
                .build();
    }
}
