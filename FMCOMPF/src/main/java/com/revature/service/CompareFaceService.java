package com.revature.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;


import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class CompareFaceService {

    String bucketName = "fm-bucket-demo";
    String image1 = "taye.jpeg";
    String image2 = "taye2.jpg";
    String image3 = "brad-pitt.jpg";


    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-west-1").build();

    public String compareSameFace(String image1, String image2) {

        String imageResultsSameFace = "";
        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(this.image1).withBucket(bucketName))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(this.image2).withBucket(bucketName))).withSimilarityThreshold(70F);

        try {

            CompareFacesResult result = rekognitionClient.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists = result.getFaceMatches();

            System.out.println( this.image1 + " " + this.image2);

            if (!lists.isEmpty()) {
                for (CompareFacesMatch label : lists) {
                    imageResultsSameFace = label.getSimilarity().toString();
                    System.out.println(label.getFace()+ label.getSimilarity().toString());

                }
            } else {
                imageResultsSameFace = ("The faces are not a match");
                System.out.println("The faces are not a match");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageResultsSameFace;
    }

    public String compareDiffFaces(String photo1, String photo3) {

        String imageResultsDiffFaces = " ";

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(this.image1).withBucket(bucketName))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(this.image3).withBucket(bucketName))).withSimilarityThreshold(70F);

        try {

            CompareFacesResult result = rekognitionClient.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists = result.getFaceMatches();

            System.out.println( this.image1 + " " + this.image3);

            if (!lists.isEmpty()) {
                for (CompareFacesMatch label : lists) {

                    imageResultsDiffFaces = label.getSimilarity().toString();

                    System.out.println(label.getFace() + label.getSimilarity().toString());
                }
            } else {
                imageResultsDiffFaces = (this.image1 + " "+ this.image3);
                System.out.println("The faces are not a match");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageResultsDiffFaces;
    }
}

