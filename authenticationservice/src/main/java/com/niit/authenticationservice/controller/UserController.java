package com.niit.authenticationservice.controller;

import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserNotFoundException;
import com.niit.authenticationservice.service.SecurityTokenGenerator;
import com.niit.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/userregister")
    public ResponseEntity<?> addingUserData(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUserDetails(user), HttpStatus.CREATED);
    }
    @GetMapping("api/v1/userData/gettingdata")
    public ResponseEntity<?> gettingAllUserData(){
        return new ResponseEntity<>(userService.getAllUserDetails(),HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<?> findByUserEmailAndPassword(@RequestBody User user) throws UserNotFoundException {
        Map <String,String> map=null;
        try {
           User user1= userService.findByUserEmailAndPassword(user.getUserEmail(),user.getPassword());
            if(user1.getUserEmail().equals(user.getUserEmail())){
                map=securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
        catch (Exception ex){
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
