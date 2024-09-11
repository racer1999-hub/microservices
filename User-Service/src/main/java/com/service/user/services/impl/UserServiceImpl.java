package com.service.user.services.impl;

import com.service.user.entities.Hotel;
import com.service.user.entities.Rating;
import com.service.user.entities.User;
import com.service.user.exception.ResourceNotFoundException;
import com.service.user.external.services.HotelService;
import com.service.user.repositories.UserRepository;
import com.service.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
       return  userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user= userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given user id not found on server !!"+ userId));
        //fetch rating of the above from RATING-SERVICE
        //http://localhost:8083/ratings/getRatingByUser/e3b2a509-b1d9-4b75-bce8-c92610374cf5

        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/getRatingByUser/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingsOfUser);
        List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList=ratings.stream().map(rating -> {
            //call to Hotel service to get Hotel by rating
            //http://localhost:8082/hotels/getHotel/3a3bf6d0-74d7-4cb9-b801-9d66b8d729f5
            //ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getHotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
           // Hotel hotel=hotelResponseEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());


        user.setRatings(ratingList);

        return user;
    }
}
