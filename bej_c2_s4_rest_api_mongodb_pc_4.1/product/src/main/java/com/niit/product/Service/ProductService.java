package com.niit.product.Service;

import com.niit.product.Exception.ProductAlreadyExistException;
import com.niit.product.Exception.ProductNotFoundException;
import com.niit.product.Model.Product;

import java.util.List;

public interface ProductService {
    //insertion
public Product insertProductDetail(Product product)throws ProductAlreadyExistException;

//fetchAll
public List<Product>getAllProducts()throws Exception;

//update
public Product updateProduct(Product product, int productId)throws ProductNotFoundException;

//delete
public boolean deleteProduct(int productId)throws ProductNotFoundException;

//get by product code
public List<Product> getByProductCode(String productCode)throws ProductNotFoundException;

//get all products that are in stock
public List<Product>getAllProductInStock(String stock)throws ProductNotFoundException;
}
