package com.niit.userauthenticationservice.controler;

import com.niit.userauthenticationservice.exception.UserAlreadyExist;
import com.niit.userauthenticationservice.exception.UserNotFound;
import com.niit.userauthenticationservice.model.User;
import com.niit.userauthenticationservice.security.SecurityTokenGenerator;
import com.niit.userauthenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v2")
public class UserController {
    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody User user) throws UserAlreadyExist {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public ResponseEntity<?> findByUserEmailAndPassword(@RequestBody User user) throws UserNotFound {
        Map<String,String> map=null;
        try {
            User user1= userService.findUserByUserNameAndPassword(user.getUserName(),user.getPassword());
            if((user1.getUserName()).equals(user.getUserName())){
                map=securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch (UserNotFound ex){
            throw new UserNotFound();
        }
        catch (Exception ex){
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
