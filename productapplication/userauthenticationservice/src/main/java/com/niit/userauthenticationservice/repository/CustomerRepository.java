package com.niit.userauthenticationservice.repository;

import com.niit.userauthenticationservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findCustomerByCustomerIdAndPassword(String customerId,String password);
}
