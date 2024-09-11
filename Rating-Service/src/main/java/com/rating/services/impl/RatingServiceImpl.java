package com.rating.services.impl;

import com.rating.entities.Rating;
import com.rating.repositories.RatingRepository;
import com.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @Override
    public Rating createRating(Rating rating) {
        String ratingId=UUID.randomUUID().toString();
        rating.setId(ratingId);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getAllByUser(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotel(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
