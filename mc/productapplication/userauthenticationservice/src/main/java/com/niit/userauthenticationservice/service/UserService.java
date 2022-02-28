package com.niit.userauthenticationservice.service;

import com.niit.userauthenticationservice.exception.UserAlreadyExist;
import com.niit.userauthenticationservice.exception.UserNotFound;
import com.niit.userauthenticationservice.model.User;

public interface UserService {
    User saveUser(User customer) throws UserAlreadyExist;
    User findUserByUserNameAndPassword(String userName, String password) throws UserNotFound;
}
