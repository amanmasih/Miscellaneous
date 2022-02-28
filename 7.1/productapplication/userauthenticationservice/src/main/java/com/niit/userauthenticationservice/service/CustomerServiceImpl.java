package com.niit.userauthenticationservice.service;

import com.niit.userauthenticationservice.exception.CustomerAlreadyExist;
import com.niit.userauthenticationservice.exception.CustomerNotFound;
import com.niit.userauthenticationservice.model.Customer;
import com.niit.userauthenticationservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExist {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByCustomerIdAndPassword(String customerId, String password) throws CustomerNotFound {
        Customer user= customerRepository.findCustomerByCustomerIdAndPassword(customerId,password);
        if(user==null){
            throw new CustomerNotFound();
        }
        return user;
    }
}
