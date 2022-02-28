package com.niit.userauthenticationservice.service;

import com.niit.userauthenticationservice.exception.CustomerAlreadyExist;
import com.niit.userauthenticationservice.exception.CustomerNotFound;
import com.niit.userauthenticationservice.model.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer) throws CustomerAlreadyExist;
    Customer findCustomerByCustomerIdAndPassword(String customerId,String password) throws CustomerNotFound;
}
