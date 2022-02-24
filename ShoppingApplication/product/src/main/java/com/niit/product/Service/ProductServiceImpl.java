package com.niit.product.Service;

import com.niit.product.Exception.ProductAlreadyExistException;
import com.niit.product.Exception.ProductNotFoundException;
import com.niit.product.Model.Product;
import com.niit.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    //constructor injection
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //insertion
    @Override
    public Product insertProductDetail(Product product) throws ProductAlreadyExistException {
        if(productRepository.findById(product.getProductId()).isPresent()){throw new ProductAlreadyExistException();}
        return productRepository.save(product);
    }

    //fetch all product in the database
    @Override
    public List<Product> getAllProducts() throws Exception {
        return productRepository.findAll();
    }

    //updatation
    @Override
    public Product updateProduct(Product product, int productId) throws ProductNotFoundException {
        if(productRepository.findById(product.getProductId()).isEmpty()){throw new ProductNotFoundException();}
        return productRepository.save(product);
    }


    //delete Product
    @Override
    public boolean deleteProduct(int productId) throws ProductNotFoundException {
       boolean flag =false;
        if(productRepository.findById(productId).isEmpty()){throw new ProductNotFoundException();}
       else{
           productRepository.deleteById(productId);
           flag=true;
        }
       return flag;
    }

    //Product Code
    @Override
    public List<Product> getByProductCode(String productCode) throws ProductNotFoundException {
        if (productRepository.fetchProductCode(productCode).isEmpty()){
            throw new ProductNotFoundException();
        }
        return productRepository.fetchProductCode(productCode);
    }
    //In stock
    @Override
    public List<Product> getAllProductInStock(String stock) throws ProductNotFoundException {
        if (productRepository.fetchProductStock(stock).isEmpty()){
            throw new ProductNotFoundException();
        }
        return productRepository.fetchProductStock(stock);
    }
}
