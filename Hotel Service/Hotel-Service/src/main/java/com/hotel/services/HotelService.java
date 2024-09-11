package com.hotel.services;

import com.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String id);
    List<Hotel> getAllHotel();
}
