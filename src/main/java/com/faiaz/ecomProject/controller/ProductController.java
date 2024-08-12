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

}
