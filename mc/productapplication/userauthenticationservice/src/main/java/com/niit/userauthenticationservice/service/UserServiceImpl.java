package com.niit.userauthenticationservice.service;

import com.niit.userauthenticationservice.exception.UserAlreadyExist;
import com.niit.userauthenticationservice.exception.UserNotFound;
import com.niit.userauthenticationservice.model.User;
import com.niit.userauthenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExist {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) throws UserNotFound {
        User user= userRepository.findUserByUserNameAndPassword(userName,password);
        if(user==null){
            throw new UserNotFound();
        }
        return user;
    }
}
