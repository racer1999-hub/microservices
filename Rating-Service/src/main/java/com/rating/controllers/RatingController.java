package com.rating.controllers;

import com.rating.entities.Rating;
import com.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping(value = "/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating createdRating=ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @GetMapping(value = "/getAllRatings")
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRating());
    }

    @GetMapping(value = "/getRatingByUser/{id}")
    public ResponseEntity<List<Rating>> getAllRatingByUser(@PathVariable(value = "id") String userId){
        List<Rating> ratingsByUser=ratingService.getAllByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingsByUser);
    }

    @GetMapping(value = "/getRatingByHotel/{id}")
    public ResponseEntity<List<Rating>> getAllRatingByHotel(@PathVariable(value = "id") String hotelId){
        List<Rating> ratingsByHotel=ratingService.getAllByHotel(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(ratingsByHotel);
    }
}
