package com.niit.product;
import com.niit.product.Exception.ProductAlreadyExistException;
import com.niit.product.Exception.ProductNotFoundException;
import com.niit.product.Model.Product;
import com.niit.product.Model.ProductDescription;
import com.niit.product.Repository.ProductRepository;
import com.niit.product.Service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

     //Controller->Service->Respository

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private ProductDescription productDescription;
    private List<Product> productList;

    @BeforeEach
    public void setUp(){
        productDescription = new ProductDescription("IVI00HN1","black","SmartPhone","yes");
        product = new Product(1,"Iphone 6",productDescription,54000);
        productList = Arrays.asList(product);
    }

    @AfterEach
    public void tearDown(){
        product=null;
        productDescription=null;
        productRepository.deleteAll();
    }

    //positive test case for insertion
    @Test
    public void givenProductToSaveReturnProduct() throws ProductAlreadyExistException{
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(null));
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product,productService.insertProductDetail(product));
        verify(productRepository,times(1)).findById(product.getProductId());
    }

    //negative test case for the insertion
    @Test
    public void givenProductToSaveWontReturnProduct(){
    when(productRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(product));
    assertThrows(ProductAlreadyExistException.class,()->productService.insertProductDetail(product));
    verify(productRepository,times(1)).findById(product.getProductId());
    verify(productRepository,times(0)).save(product);
    }

    //positive cases for deletion of the product
    @Test
    public void givenProductToDeleteReturnTrue() throws ProductNotFoundException{
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(product));
        boolean flag = productService.deleteProduct(product.getProductId());
        assertTrue(flag);
        verify(productRepository,times(1)).findById(product.getProductId());
        verify(productRepository,times(1)).deleteById(product.getProductId());
    }

    //negative test cases for delete
    @Test
    public void givenProductToDeleteReturnFalse(){
     when(productRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(null));
     assertThrows(ProductNotFoundException.class,()->productService.deleteProduct(product.getProductId()));
     verify(productRepository,times(1)).findById(product.getProductId());
     verify(productRepository,times(0)).deleteById(product.getProductId());
    }

    //positive test cases fro updatation
    @Test
    public void givenProductToUpdate()throws ProductNotFoundException{
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(product));
        product.setProductPrice(50200);
        when(productRepository.save(product)).thenReturn(product);
        assertEquals(product,productService.updateProduct(product,product.getProductId()));
        assertEquals(50200,productService.updateProduct(product,product.getProductId()).getProductPrice());
        verify(productRepository,times(2)).findById(product.getProductId());
        verify(productRepository,times(2)).save(product);

    }

    //negative test cases for updatation
    @Test
    public void givenProductWontUpdate(){
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.ofNullable(null));
        assertThrows(ProductNotFoundException.class,()->productService.updateProduct(product,product.getProductId()));
        verify(productRepository,times(1)).findById(product.getProductId());
        verify(productRepository,times(0)).save(product);
    }

    //get the data in the list
    @Test
    public void getAllProducts()throws Exception{
        when(productRepository.findAll()).thenReturn(Optional.ofNullable(productList).get());
        assertEquals(productList,productService.getAllProducts());
    }

    @Test
    public void getAllProductFailure() throws Exception {
        when(productRepository.findAll()).thenReturn(null);
        assertNotEquals(productList,productService.getAllProducts());
    }

    //positive test cases for product Code
    @Test
    public void getproductCodePass() throws ProductNotFoundException {
        when(productRepository.fetchProductCode(product.getProductDescription().getProductCode())).thenReturn(Optional.ofNullable(productList).get());
        assertEquals(productList,productService.getByProductCode("IVI00HN1"));
        verify(productRepository,times(2)).fetchProductCode("IVI00HN1");
    }

    //negative testcases for productCode
    @Test
    public void getproductCodeFailure() throws Exception {
        when(productRepository.fetchProductCode(product.getProductDescription().getProductCode())).thenReturn(null);
        assertThrows(Exception.class,()->productService.getByProductCode("IVI00HN1"));
//        assertNotEquals(productList,productService.getByProductCode("IVII00HN1"));
        verify(productRepository,times(1)).fetchProductCode("IVI00HN1");
    }

    //positive test cases product in stock
    @Test
    public void productByStock() throws ProductNotFoundException {
        when(productRepository.fetchProductStock(product.getProductDescription().getInStock())).thenReturn(Optional.ofNullable(productList).get());
        assertEquals(productList,productService.getAllProductInStock("yes"));
        verify(productRepository,times(2)).fetchProductStock("yes");
    }

    //negative test cases for product in stock
    @Test
    public void productByStockFailure() throws Exception {
        when(productRepository.fetchProductStock(product.getProductDescription().getInStock())).thenReturn(isNotNull());
        assertThrows(Exception.class,()->productService.getAllProductInStock(product.getProductDescription().getInStock()));
        verify(productRepository,times(1)).fetchProductStock(product.getProductDescription().getInStock());
    }

}//end of the class