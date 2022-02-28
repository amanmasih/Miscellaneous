package com.niit.userauthenticationservice.controler;

import com.niit.userauthenticationservice.exception.CustomerAlreadyExist;
import com.niit.userauthenticationservice.exception.CustomerNotFound;
import com.niit.userauthenticationservice.model.Customer;
import com.niit.userauthenticationservice.security.SecurityTokenGenerator;
import com.niit.userauthenticationservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v2/cutomerData/")
public class CustomerController {
    CustomerService customerService;
    SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public CustomerController(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator) {
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody Customer customer) throws CustomerAlreadyExist {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public ResponseEntity<?> findByUserEmailAndPassword(@RequestBody Customer customer) throws CustomerNotFound {
        Map<String,String> map=null;
        try {
            Customer customer1= customerService.findCustomerByCustomerIdAndPassword(customer.getCustomerId(),customer.getPassword());
            if((customer1.getCustomerId()).equals(customer.getCustomerId())){
                map=securityTokenGenerator.generateToken(customer);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch (CustomerNotFound ex){
            throw new CustomerNotFound();
        }
        catch (Exception ex){
            return new ResponseEntity<>("Please try after sometime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
