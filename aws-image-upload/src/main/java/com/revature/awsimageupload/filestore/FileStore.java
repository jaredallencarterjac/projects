package com.revature.awsimageupload.filestore;

//using Amazon config class to store the image

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;



    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    //save method, saves files to amazon s3
    //path = bucket name and set of folders you may have
    //metadata you want to pass for the images you want to pass
    //InputStream is what gets save to the amazon s3 bucket
    public void save(String path, String fileName,
                     Optional<Map<String, String>> optionalMetadata, InputStream inputStream){
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map ->{
                if (!map.isEmpty()){
                    //passing the key and value from the map which is the optionalMetadata, just using method reference
                    map.forEach(metadata:: addUserMetadata);

                }
                });
        try{
            s3.putObject(path,fileName, inputStream, metadata);
        } catch (AmazonServiceException e){
            throw new IllegalStateException("Storing content was failed", e);

        }

    }

    //now going to build the front end
}
