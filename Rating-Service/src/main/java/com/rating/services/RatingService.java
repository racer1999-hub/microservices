package com.rating.services;

import com.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    //getAll Ratings
    List<Rating> getAllRating();

    //get All Rating By User
    List<Rating> getAllByUser(String userId);

    //get All Rating By Hotel
    List<Rating> getAllByHotel(String hotelId);

}
