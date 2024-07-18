package com.service.user.services;

import com.service.user.entities.User;

import java.util.List;

public interface UserService {
    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);

    // delete
    // update
}
