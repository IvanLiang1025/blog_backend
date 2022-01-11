package com.ivan.sunnyblog.web.res;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Author: jinghaoliang
 **/

@Component
public class S3ResourceManager {

    private static Logger logger = LoggerFactory.getLogger(S3ResourceManager.class);

    @Autowired
    static AmazonS3 s3Client;

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.path}")
    private String path;

    @Value("${s3.bucket.avatarPath}")
    private String avatarPath;

    @Value("${aws.region.static}")
    private String region;

//    static {
////        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
//        s3Client = AmazonS3ClientBuilder.standard()
////                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withCredentials(new AWSCredentialsProviderChain())
//                .withRegion("ca-central-1").build();
//    }

    public String uploadToS3(MultipartFile multipartFile) throws Exception{
        String fileName = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        String bucketPath = bucketName;
        if(StringUtils.isNotBlank(path)){
            bucketPath = bucketPath + path;
        }

        String fileKey = generateFileKey(fileName);

        logger.info("uploading file to s3");
        PutObjectResult putObjectResult = s3Client.putObject(bucketPath, fileKey, inputStream, objectMetadata);

        URL url = s3Client.getUrl(bucketPath, fileKey);

        logger.info("uploading done. returned url: {}", url);

        return url.toString();

    }

    public String generateFileKey(String fileName){
        if(StringUtils.isBlank(fileName)){
            return null;
        }

        return "blog_"+System.currentTimeMillis()+"_"+fileName;
    }

    public String getAvatarUrl() {
//        https://res-blog-public.s3.ca-central-1.amazonaws.com/avatar/1.png
        int num = (int)(Math.random() * 100 + 1);
        String url = getS3EndPoint() + avatarPath + "/" + num + ".png";
        return url;
    }

    public String getS3EndPoint() {
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder.append(bucketName).append(".s3.").append(region).append(".amazonaws.com");

        return stringBuilder.toString();
    }


//    public List<String> getObjectKeys (){
////        ObjectListing objectListing = s3Client.listObjects(bucket);
//        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(1);
//        ListObjectsV2Result result;
//
//        do {
//            result = s3Client.listObjectsV2(listObjectsV2Request);
//            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
//                System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
//            }
//        }while (result.isTruncated());
//
//        return null;
//    }
}
