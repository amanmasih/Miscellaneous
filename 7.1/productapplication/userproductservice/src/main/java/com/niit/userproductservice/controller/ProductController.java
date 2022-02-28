package com.niit.userproductservice.controller;

import com.niit.userproductservice.exception.CustomerAlreadyExist;
import com.niit.userproductservice.exception.ProductAlreadyExist;
import com.niit.userproductservice.exception.CustomerNotFound;
import com.niit.userproductservice.model.Customer;
import com.niit.userproductservice.model.Product;
import com.niit.userproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/v1")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> saveProductDetails(@RequestBody Customer customer) throws  CustomerAlreadyExist {
        try {
            return  new ResponseEntity<>(productService.registerNewCustomer(customer), HttpStatus.CREATED);
        }
        catch(CustomerAlreadyExist e)
        {
            throw new CustomerAlreadyExist();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cutomerData/saveproduct/{customerId}")
    public ResponseEntity<?> saveProductDetails(@RequestBody Product product,@PathVariable String customerId) throws CustomerNotFound {
        try {
            return  new ResponseEntity<>(productService.saveCustomerProduct(customerId,product), HttpStatus.CREATED);
        }
        catch(CustomerNotFound e)
        {
            throw new CustomerNotFound();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cutomerData/getproduct")
    public ResponseEntity<?> getAllProduct(){
        try{
            return new ResponseEntity<>(productService.getAllProductsOfACustomer(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return  new ResponseEntity<>("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/cutomerData/product/{customerId}/{productCode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId,@PathVariable int productCode)throws CustomerNotFound
    {
        try{
            return new ResponseEntity<>(productService.deleteProductOfACustomer(customerId,productCode),HttpStatus.OK);
        }
        catch (CustomerNotFound e){
            throw new CustomerNotFound();
        }
        catch(Exception e){
            return new ResponseEntity<>("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
