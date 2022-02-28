package com.niit.userauthenticationservice.security;


import com.niit.userauthenticationservice.model.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(Customer customer);
}
