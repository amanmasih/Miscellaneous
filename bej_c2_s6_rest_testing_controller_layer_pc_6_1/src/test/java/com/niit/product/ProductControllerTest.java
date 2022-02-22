package com.niit.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.product.Controller.ProductController;
import com.niit.product.Exception.ProductAlreadyExistException;
import com.niit.product.Exception.ProductNotFoundException;
import com.niit.product.Model.Product;
import com.niit.product.Model.ProductDescription;
import com.niit.product.Service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    //to create a bean
    @Autowired
    private MockMvc mockMvc;


    private Product product;
    private ProductDescription productDescription;
    private List<Product> productList;

    @BeforeEach
    public void setUp(){
        productDescription = new ProductDescription("IVI00HN1","black","SmartPhone","yes");
        product = new Product(1,"Iphone 6",productDescription,54000);
        productList = Arrays.asList(product);

        //to build the instance of the productController as in just like using inside a server without
        // executing the complete application Run

        mockMvc= MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    public void tearDown(){
        product=null;
        productDescription=null;
    }

    //method for conversion from Domain/Model/Pojo Object to JSON formatted Fashioned Object
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result=null;
        try {
            ObjectMapper mapper = new ObjectMapper(); // provides functionality for reading and writing JSON,
            String jsonContent = mapper.writeValueAsString(obj);
            result=jsonContent;
        }
        catch (JsonProcessingException e){
            result="error while conversion";
        }
        return result;
    }

   //positive test cases for insertion of data
    @Test
    public void givenProductToSaveReturnProduct() throws Exception{
        when(productService.insertProductDetail(any())).thenReturn(product);
        mockMvc.perform(post("/api/v1/product/insert")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(product)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print()); //actions
        verify(productService,times(1)).insertProductDetail(any());
    }
    //negative test cases for insertion of data
    @Test
    public void givenProductToSaveReturnProductFailure()throws Exception
    {
        when(productService.insertProductDetail(any())).thenThrow(ProductAlreadyExistException.class);
        mockMvc.perform(post("/api/v1/product/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).insertProductDetail(any());
    }

    //positive test cases for deletion of the product
    @Test
    public void givenProductCodeReturnProductDeleteSuccess() throws Exception
    {
        when(productService.deleteProduct(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/product/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).deleteProduct(anyInt());
    }

    //negative test cases for deltetion of Product data
    @Test
    public void givenProductToDeleteReturnDeleteFailure()throws Exception {
        when(productService.deleteProduct(1)).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(delete("/api/v1/product/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).deleteProduct(1);
    }

    //positive test cases for updatation of product data
    @Test
    public void givenProductToUpdateReturnProduct() throws Exception{
        when(productService.updateProduct(any(),anyInt())).thenReturn(product);
        mockMvc.perform(put("/api/v1/product/update/1")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(product)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).updateProduct(any(),anyInt());
    }

    //negative test cases for updatation of product data
    @Test
    public void givenProductToUpdateReturnProductFailure() throws Exception
    {
        when(productService.updateProduct(any(),anyInt())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(put("/api/v1/product/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product)))
                .andExpect(status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).updateProduct(any(),anyInt());
    }


    @Test
    public void getproductCodeSuccess() throws Exception {
        when(productService.getByProductCode(anyString())).thenReturn(productList);
        mockMvc.perform(get("/api/v1/product/productcode/IVI00HN1")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(product))
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getByProductCode(anyString());
    }

    //negative testcases for Product code
    @Test
    public void getproductCodeFailure() throws Exception {
        when(productService.getByProductCode(anyString())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/api/v1/product/productcode/IVI00HN1")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(product))
        ).andExpect(status().is5xxServerError()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getByProductCode(anyString());
    }


    //positive test cases for Product stock
    @Test
    public void getproductByStockSuccess() throws Exception {
        when(productService.getAllProductInStock(anyString())).thenReturn(productList);
        mockMvc.perform(get("/api/v1/product/stockcheck/yes")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(product))
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getAllProductInStock(anyString());
    }

    //negative test cases for Product stock Failure
    @Test
    public void getproductByStockFailure() throws Exception {
        when(productService.getAllProductInStock(anyString())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/api/v1/product/stockcheck/yes")
                .contentType(MediaType.APPLICATION_JSON).content(jsonToString(product))
        ).andExpect(status().is5xxServerError()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getAllProductInStock(anyString());
    }

}//end of class
