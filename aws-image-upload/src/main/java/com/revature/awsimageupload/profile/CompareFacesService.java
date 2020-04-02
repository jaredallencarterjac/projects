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

    String image3 = "taye.jpg";

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

    public String compareOneAndTwo(String pic1, String pic3) {

        String output2 = "";
        //Comparing photo 1 and photo 3;
        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo1).withBucket(bucketName))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo3).withBucket(bucketName))).withSimilarityThreshold(80F);

        try{

            CompareFacesResult result = rekognitionClient.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists = result.getFaceMatches();

            System.out.println("Detected labels for " + photo1 + " and " + photo3);

            if (!lists.isEmpty()) {
                for (CompareFacesMatch label : lists) {
                    output2 = label.getSimilarity().toString();
                    System.out.println(label.getFace() + ": Similarity is " + label.getSimilarity().toString());
                    System.out.println("Photo1" + photo1 + "photo3" + photo3 + "similarity score" + label.getSimilarity().toString());
                }
            } else {
                output2 = (photo1 + " " + photo3 + ": FACES DO NOT MATCH");
                System.out.println("Faces do not match!");
            }
        } catch (Exception e){
            System.out.println("error");
        }
        return output2;
    }





}


