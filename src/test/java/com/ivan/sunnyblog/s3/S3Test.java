package com.ivan.sunnyblog.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Author: jinghaoliang
 **/

public class S3Test {

    final static AmazonS3 s3;
    final static String AWS_ACCESS_KEY = "AKIATP4TEDJW32XORCJ4"; // 【你的 access_key】
    final static String AWS_SECRET_KEY = "dhzaV/SeMar6qcN5ubZJerTKZ0z6m/KIBt2N9RSm"; // 【你的 aws_secret_key】

    static String bucketName = "res-blog-public"; // 【你 bucket 的名字】 # 首先需要保证 s3 上已经存在该存储桶
    static String pathName = "/res-dev"; // 【你 bucket 的名字】 # 首先需要保证 s3 上已经存在该存储桶
    static String rootUrl = "http://localhost:8081";
    static String region = "ca-central-1";
    static {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region).build();
    }

    @Test
    public void test() {
        String path = "/Users/jinghaoliang/Desktop/test.png";
        File file = new File(path);
//        String = StringUtils.substringAfterLast(path, ".");
        try
                (
                FileInputStream inputStream = new FileInputStream(file))
               {
            PutObjectResult putObjectResult = s3.putObject(bucketName + pathName, "1.png", file);
            System.out.println(putObjectResult.toString());
//

            URL url = s3.getUrl(bucketName + pathName, "1.png");
            System.out.println(url.toString());


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
