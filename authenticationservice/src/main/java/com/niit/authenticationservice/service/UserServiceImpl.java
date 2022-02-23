package com.niit.authenticationservice.service;

import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserNotFoundException;
import com.niit.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUserDetails(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUserDetails(){
        return userRepository.findAll();
    }

    @Override
    public User findByUserEmailAndPassword(String userEmail, String password) throws UserNotFoundException {
        User user= userRepository.findByUserEmailAndPassword(userEmail,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
