package com.revature.controller;

import com.revature.service.CompareFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CompareFaceController {

    private CompareFaceService compareFaceService;

    @Autowired
    public CompareFaceController(CompareFaceService compareFaceService) {
        this.compareFaceService = compareFaceService;
    }

    @RequestMapping("/face-same")
    public String getSameFace() {
        return compareFaceService.compareSameFace("taye.jpeg", "taye2.jpg");
    }

    @RequestMapping("/face-different")
    public String getDifferentFace() {
        return compareFaceService.compareDiffFaces("taye.jpeg", "brad-pitt.jpg");
    }
}