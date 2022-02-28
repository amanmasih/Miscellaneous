package com.niit.userauthenticationservice.security;


import com.niit.userauthenticationservice.model.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(Customer customer) {

        String jwstToken=Jwts.builder().setSubject(customer.getCustomerId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretekeyid").compact();
       Map<String,String> mapData=new HashMap<>();
       mapData.put("token",jwstToken);
       mapData.put("message","Authentication Successfull");
       return mapData;
    }
}
