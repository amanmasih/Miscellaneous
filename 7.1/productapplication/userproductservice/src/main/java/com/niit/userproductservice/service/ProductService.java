package com.niit.userproductservice.service;

import com.niit.userproductservice.exception.CustomerAlreadyExist;
import com.niit.userproductservice.exception.ProductAlreadyExist;
import com.niit.userproductservice.exception.CustomerNotFound;
import com.niit.userproductservice.model.Customer;
import com.niit.userproductservice.model.Product;

import java.util.List;

public interface ProductService {
    Customer registerNewCustomer(Customer customer) throws CustomerAlreadyExist;
    Customer saveCustomerProduct(String customerId,Product product) throws CustomerNotFound;
    List<Customer> getAllProductsOfACustomer() throws Exception;
    Customer deleteProductOfACustomer(String customerId,int ProductCode) throws Exception;

}
