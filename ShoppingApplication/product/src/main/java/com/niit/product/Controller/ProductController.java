package com.niit.product.Controller;

import com.niit.product.Exception.ProductAlreadyExistException;
import com.niit.product.Exception.ProductNotFoundException;
import com.niit.product.Model.Product;
import com.niit.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/product")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertProductDetail(@RequestBody Product product)throws ProductAlreadyExistException{
        try{
          return new ResponseEntity<>(productService.insertProductDetail(product), HttpStatus.CREATED);
        }

        catch(ProductAlreadyExistException ex){
            throw new ProductAlreadyExistException();
        }
        catch(Exception e){
            return new ResponseEntity<>("Unexpected Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//end of function

    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllProducts()
    {
        try{
            return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return  new ResponseEntity<>("Unexpected Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//end of the function

    //updatation
    @PutMapping("/update/{productId}")
    public ResponseEntity<?>updateProduct(@RequestBody Product product,@PathVariable int productId)throws ProductNotFoundException{
        try {
            return new ResponseEntity<>(productService.updateProduct(product,productId),HttpStatus.OK);
        }
        catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        catch(Exception e){
            return new ResponseEntity<>("Unexpected error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //deletion
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId )throws ProductNotFoundException
    {
        try{
            return new ResponseEntity<>(productService.deleteProduct(productId),HttpStatus.OK);
        }
        catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        catch(Exception e){
            return new ResponseEntity<>("Unexpected Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }//end of the function

    //fetch on the basis of product code
    @GetMapping("/productcode/{productCode}")
    public ResponseEntity<?>getByProductCode(@PathVariable String productCode)
    {
        try{
            return new ResponseEntity<>(productService.getByProductCode(productCode),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Unexpected error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //fetch on the products that are in Stock
    @GetMapping("/stockcheck/{stock}")
    public ResponseEntity<?> getProductInStock(@PathVariable String stock)
    {
        try{
            return new ResponseEntity<>(productService.getAllProductInStock(stock),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Unexpected error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }//End of the function


}//end of the class
