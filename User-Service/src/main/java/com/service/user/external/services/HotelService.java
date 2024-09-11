package com.service.user.external.services;

import com.service.user.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/getHotel/{hotelId}")
    Hotel getHotel(@PathVariable(name="hotelId") String hotelId);
}
