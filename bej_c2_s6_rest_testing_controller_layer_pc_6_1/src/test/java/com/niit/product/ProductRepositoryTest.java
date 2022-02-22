package com.niit.product;

import com.niit.product.Model.Product;
import com.niit.product.Model.ProductDescription;
import com.niit.product.Repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private ProductDescription productDescription;


    @BeforeEach
    public void setUp(){
        productDescription = new ProductDescription("IVI00HN1","black","SmartPhone","yes");
        product = new Product(1,"Iphone 6",productDescription,54000);
    }

    @AfterEach
    public void tearDown(){
        product=null;
        productDescription=null;
        productRepository.deleteAll();
    }

    //positive test cases for insertion
    @Test
    public void givenProductToSaveAndReturnTheProduct(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        assertEquals(product.getProductId(),product1.getProductId());
        assertEquals("IVI00HN1",product.getProductDescription().getProductCode());
    }

    //negative test cases for insertion
    @Test
    public void givenProductWontSaveAndReturnTheProduct(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        assertNotNull(product1);
        assertNotEquals(0,product1.getProductId());
        assertNotEquals(2,product1.getProductId());
        assertNotEquals("IVII00HN1",product.getProductDescription().getProductCode());
    }


//positive test cases for getting all products
    @Test
    public void givenReturnAllProducts(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        List<Product> list =productRepository.findAll();
        assertEquals(1,list.size());
        assertEquals("Iphone 6",list.get(0).getProductName());
        assertEquals(54000,list.get(0).getProductPrice());
        assertEquals("IVI00HN1",list.get(0).getProductDescription().getProductCode());
    }

    //negative cases for getting all products
    @Test
    public void givenWontReturnAllProducts(){
     productRepository.save(product);
     Product product1 = productRepository.findById(product.getProductId()).get();
     List<Product> list = productRepository.findAll();
     assertNotEquals(0,list.size());
     assertNotEquals("Iphone6",list.get(0).getProductName());
     assertNotEquals("54K",list.get(0).getProductPrice());
     assertNotEquals("IVII00HN1",list.get(0).getProductDescription().getProductCode());
    }

    //positive test cases for delete cases
    @Test
    public void givenProductToDeleteProduct(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        productRepository.delete(product1);
        assertEquals(Optional.empty(),productRepository.findById((product.getProductId())));
    }

    //negative test case for delete
    @Test
    public void givenProductWontDeleteProduct(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        productRepository.delete(product1);
        assertNotEquals(1,productRepository.findById((product.getProductId())));
    }

    //positive test cases to update the Product
    @Test
    public void givenProductToUpdateProduct(){
        productRepository.save(product);
        productDescription = new ProductDescription("IX00HN1","black","SmartPhone","yes");
        product = new Product(1,"Iphone X",productDescription,50000);
        Product product1 = productRepository.findById(product.getProductId()).get();
        productRepository.save(product);
        assertEquals(product.getProductId(),product1.getProductId());
        assertEquals("Iphone X",product.getProductName());
        assertEquals("IX00HN1",product.getProductDescription().getProductCode());
        assertEquals(50000,product.getProductPrice());
    }

    //negative test cases to update the Product
    @Test
    public void givenProductWontUpdateProduct(){
        productRepository.save(product);
        product = new Product(1,"Iphone 6",productDescription,50000);
        Product product1 = productRepository.findById(product.getProductId()).get();
        productRepository.save(product);
        assertNotNull(product);
        assertNotEquals(0,product1.getProductId());
        assertNotEquals(2,product1.getProductId());
        assertNotEquals(54000,product.getProductPrice());
    }

    //positive test cases for productCode
    @Test
    public void givenProductCodeReturnProductList(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        List<Product> list = productRepository.fetchProductCode("IVI00HN1");
        assertEquals("IVI00HN1",list.get(0).getProductDescription().getProductCode());
    }

    //negative test case for productcode
    @Test
    public void givenProductCodeWontReturnProductList(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        List<Product> list = productRepository.fetchProductCode("IVI00HN1");
        assertNotEquals("IVII00HN1",list.get(0).getProductDescription().getProductCode());
    }

    //positive test cases for product in stock
    @Test
    public void givenProductStockReturnProductList(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        List<Product> list = productRepository.fetchProductStock("yes");
        assertEquals("yes",list.get(0).getProductDescription().getInStock());
    }

    //negative test case for product in stock
    @Test
    public void givenProductStockWontReturnProductList(){
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        List<Product> list = productRepository.fetchProductStock("yes");
        assertNotEquals("No",list.get(0).getProductDescription().getInStock());
        assertNotEquals("no",list.get(0).getProductDescription().getInStock());
        assertNotEquals("YES",list.get(0).getProductDescription().getInStock());
        assertNotEquals("Yes",list.get(0).getProductDescription().getInStock());
    }

}//end of class
