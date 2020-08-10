package com.example.demo.Places;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/result")
public class PlaceController {

    @Autowired
    PlaceRepository repository;

    //Everything for Create and Read Routes
    // @GetMapping("search/{scenery}/{budgetLimit}")
    // public List<PlaceData> findBySceneryAndBudgetLimit(@PathVariable String scenery, @PathVariable String budgetLimit) {
    //     if(scenery.equals("all") && budgetLimit.equals("all")) {
    //         return repository.findByBudgetLimit(budgetLimit);
    //     }
    //     else if() 
    // }   

        

    
    @CrossOrigin
    @GetMapping("find/{scenery}/{budgetLimit}")
    public List<PlaceData> findBySceneryAndBudgetLimitLessThanEqual(@PathVariable String scenery, @PathVariable("budgetLimit") int budgetLimit) {
        //int budgetLimitInt = Integer.parseInt(budgetLimit);
        // int incrementedValue = budgetLimit + 555;
        // System.out.println("incoming value plus 555:"+ incrementedValue);
        if(scenery.equals("all") && budgetLimit >= 5000){
            return repository.findAll();
        }
        else if (scenery.equals("all") && budgetLimit < 5000){
            return repository.customQuery(budgetLimit);
        }
        else if (!scenery.equals("all") && budgetLimit >= 5000){
            return repository.findByScenery(scenery);
        }
        else {
            return repository.findBySceneryAndBudgetLimitLessThanEqual(scenery, budgetLimit);
        }
        

    }

    @GetMapping("scenery/{scenery}")
    public List<PlaceData> getByScenery(@PathVariable String scenery){
        System.out.println("Here is the choice coming in from user: "+scenery);
        
        if (scenery.equals("all")) {
            System.out.println("we have chosen the all option");
            return repository.findAll();
        } 
        else {
            return repository.findByScenery(scenery);
        }
    }

    @GetMapping
    public List<PlaceData> readAllPlaces() {
        return repository.findAll();
    }

    @GetMapping("/{scenery}/{arrival}")
    public List<PlaceData> getBySceneryAndArrival(@PathVariable String scenery, @PathVariable String arrival) {
        return repository.findBySceneryAndArrival(scenery, arrival);
    }

    @GetMapping("/{id}")
    public PlaceData readPlaceById(@PathVariable UUID id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public PlaceData createPlace(@RequestBody PlaceData place) {
        return repository.save(place);
    }

    //Update and Delete code

    @PutMapping("/{id}")
    public PlaceData updatePlace(@PathVariable UUID id, @RequestBody PlaceData place) {
        PlaceData placeToEdit = repository.findById(id).orElse(null);

        if(placeToEdit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place Data not Found.");
        
        }
        placeToEdit.setScenery(place.getScenery());
        placeToEdit.setarrival(place.getarrival());
        placeToEdit.setdeparture(place.getdeparture());
        placeToEdit.setbudgetLimit(place.getbudgetLimit());
        placeToEdit.setname(place.getname());
        placeToEdit.setactivities(place.getactivities());
        return repository.save(placeToEdit);
    }

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable UUID id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
    }



}