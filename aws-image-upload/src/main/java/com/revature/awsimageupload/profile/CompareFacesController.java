package com.revature.awsimageupload.profile;

import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/")
public class CompareFacesController {

    private CompareFacesService compareFacesService;

    @Autowired
    public CompareFacesController(CompareFacesService compareFacesService) {
        this.compareFacesService = compareFacesService;
    }



    //@RequestMapping annotation is used for mapping web requests to particular handler classes or handler methods.
    // The classes or methods that are annotated with @RequestMapping will be scanned and registered as the URI mappings for that specific class or method.
    @RequestMapping("/comparefaces")
    public String getimages() {
        return compareFacesService.compareFaces("taye.jpeg", "brad-pitt.jpg");
    }


}
