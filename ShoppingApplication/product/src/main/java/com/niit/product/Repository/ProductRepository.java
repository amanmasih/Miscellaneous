package com.niit.product.Repository;

import com.niit.product.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,Integer> {

    @Query("{'productDescription.productCode':{$in:[?0]}}")
    List<Product>fetchProductCode(String productCode);

    @Query("{'productDescription.inStock':{$in:[?0]}}")
    List<Product>fetchProductStock(String stock);

}
