package com.hotel.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private String id;

    @Column(name = "hotel_name")
    private String name;

    private String location;
    private String about;
}
