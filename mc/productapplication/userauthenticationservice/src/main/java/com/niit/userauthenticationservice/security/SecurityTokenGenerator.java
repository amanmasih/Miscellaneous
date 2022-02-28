package com.niit.userauthenticationservice.security;

import com.niit.userauthenticationservice.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
