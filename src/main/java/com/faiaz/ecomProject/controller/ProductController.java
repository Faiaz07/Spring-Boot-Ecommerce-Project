package com.faiaz.ecomProject.controller;

import com.faiaz.ecomProject.model.Product;
import com.faiaz.ecomProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet() {
        return "Welcome to Ecom Project";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>>  getAllProducts() {
        List<Product> productList = service.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId) {
        Product productDetails = service.getProductDetailsById(prodId);

        if(productDetails!=null){
            return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            Product newProduct =  service.addProduct(product, imageFile);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte []> getImageByProductId(@PathVariable int productId){
        Product product = service.getProductDetailsById(productId);

        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }
   @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProductDetails(@PathVariable int productId,
                                                       @RequestPart Product product,
                                                       @RequestPart MultipartFile imageFile) {
       Product updatedProductDetails = null;
       try {
           updatedProductDetails = service.updateProductDetails(productId, product, imageFile);
       } catch (IOException e) {
           return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
       }

       if(updatedProductDetails!=null){
           return new ResponseEntity<>("Product Updated", HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>("Failed To Update", HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/product/{prodcutId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodcutId){
        Product product = service.getProductDetailsById(prodcutId);
        if (product!=null) {
            service.deleteProduct(prodcutId);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }

    }

}
