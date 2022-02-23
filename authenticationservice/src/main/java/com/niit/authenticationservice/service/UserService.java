package com.niit.authenticationservice.service;

import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User saveUserDetails(User user);
    List<User> getAllUserDetails();
    User findByUserEmailAndPassword(String userEmail,String password) throws UserNotFoundException;

}
