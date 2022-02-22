package com.niit.product.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,reason="Product doesn't exist")
public class ProductNotFoundException extends Exception{
}
