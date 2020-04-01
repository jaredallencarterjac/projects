package com.revature.awsimageupload.profile;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CompareFacesService {

    //name of s3 bucket
    String bucketName = "fm-bucket-demo";

    //source image
    String image1 = "taye.jpeg";

    //target image
    String image2 = "brad-pitt.jpg";

    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion("us-west-1").build();

    public String compareFaces(String image1, String image2) {

        String imageResult = "";
        //uploading 2 images,passing the source and target images
        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                //passing name of photo
                .withS3Object(new S3Object().withName(image1).withBucket(bucketName))).withTargetImage(new Image()
                .withS3Object(new S3Object().withName(image2).withBucket(bucketName))).withSimilarityThreshold(70F);

        try {
            //comparing the results, passing the request
            CompareFacesResult result = rekognitionClient.compareFaces(compareFacesRequest);

            //Storing the results from list with type compare face match
            List<CompareFacesMatch> imageList = result.getFaceMatches();

            //means we do have a similarity

            if (!imageList.isEmpty()) {

                //matching from the list and comparing the results
                for (CompareFacesMatch compareFacesMatch : imageList) {
                    System.out.println(compareFacesMatch.getFace() + " The similarity is " + compareFacesMatch.getSimilarity().toString());
                }
            } else {
                System.out.println("No match");
            }
        } catch (
                AmazonRekognitionException e) {
            e.printStackTrace();


        }
        return imageResult;
    }


}


