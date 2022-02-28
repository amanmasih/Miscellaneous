package com.niit.userproductservice.repository;

import com.niit.userproductservice.model.Customer;
import com.niit.userproductservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Customer,String> {
    @Query("{'productList.productCode':{$in :[?0]}}")
    Product findAllProductFromProductCode(int productCode);

}
