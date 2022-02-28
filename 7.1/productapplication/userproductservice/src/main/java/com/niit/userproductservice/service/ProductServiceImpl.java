package com.niit.userproductservice.service;

import com.niit.userproductservice.exception.CustomerAlreadyExist;
import com.niit.userproductservice.exception.ProductAlreadyExist;
import com.niit.userproductservice.exception.CustomerNotFound;
import com.niit.userproductservice.model.Customer;
import com.niit.userproductservice.model.Product;
import com.niit.userproductservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Customer registerNewCustomer(Customer customer) throws CustomerAlreadyExist {
        if(productRepository.findById(customer.getCustomerId()).isPresent())
        {
            throw new CustomerAlreadyExist();
        }
        return productRepository.save(customer);
    }

    @Override
    public Customer saveCustomerProduct(String customerId,Product product) throws CustomerNotFound {
        if(productRepository.findById(customerId).isEmpty()) {
            throw new CustomerNotFound();
        }
           Customer customer=productRepository.findById(customerId).get();
            System.out.println(customer);
            System.out.println(customer.getProductList());
            if(customer.getProductList()==null){
                customer.setProductList(Arrays.asList(product));
            }
            else{
                List<Product> arrayData=customer.getProductList();
                arrayData.add(product);
                customer.setProductList(arrayData);
            }
        return productRepository.save(customer);
    }

    @Override
    public List<Customer> getAllProductsOfACustomer() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public Customer deleteProductOfACustomer(String customerId,int productCode) throws Exception {
        Product product1=null;
        System.out.println(productCode);
        if(productRepository.findById(customerId).isEmpty()) {
            throw new CustomerNotFound();
        }
        Customer customer=productRepository.findById(customerId).get();
            List<Product> arrayData=customer.getProductList();
            for(Product p:arrayData){
                if(p.getProductCode()==productCode){
                    product1=p;
                    break;
                }
            }
            if (product1==null){
                throw new Exception();
            }
            else {

            // System.out.println(product1);
            arrayData.remove(product1);
             System.out.println(arrayData);
            customer.setProductList(arrayData);
        }
        return productRepository.save(customer);
    }
}
