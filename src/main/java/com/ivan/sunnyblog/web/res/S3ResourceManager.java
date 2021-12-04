package com.ivan.sunnyblog.web.res;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;

/**
 * Author: jinghaoliang
 **/

@Component
public class S3ResourceManager {

    private static Logger logger = LoggerFactory.getLogger(S3ResourceManager.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.path}")
    private String path;

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
}
