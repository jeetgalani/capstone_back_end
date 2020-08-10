package com.example.demo;

import com.example.demo.Places.PlaceData;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @CrossOrigin
    @GetMapping("/resultz")
    public PlaceData getServerMessage() {
        return new PlaceData("Oceanfront", "07-31-20", "08-01-20", 5000, "Virigina Beach", "Swimming");
    }

    @PostMapping("/resultz")
    public PlaceData recieveMessage(@RequestBody PlaceData place) {
        return new PlaceData(place.getScenery(), place.getarrival(), place.getdeparture(), place.getbudgetLimit(), place.getname(), place.getactivities());
        
    }
    
}