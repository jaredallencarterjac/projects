package com.revature.awsimageupload.bucket;

public enum BucketName {

    //enum is taking in this specific value
    PROFILE_IMAGE("revaturecode-image-upload");


    private final String bucketName;

    //Enum that takes in a bucket name
    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

    //create service to store image to this bucket
}
