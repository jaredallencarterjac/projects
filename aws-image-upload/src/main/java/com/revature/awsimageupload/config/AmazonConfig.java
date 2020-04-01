package com.revature.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    //gives us s3 client so we can us it
    @Bean
    public AmazonS3 s3() {
        //passing access and secret key
        AWSCredentials awsCredentials = new BasicAWSCredentials(
             "",
                ""
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
 }
