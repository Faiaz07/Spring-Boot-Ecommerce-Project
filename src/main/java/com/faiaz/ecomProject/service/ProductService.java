package com.faiaz.ecomProject.service;

import com.faiaz.ecomProject.model.Product;
import com.faiaz.ecomProject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
       return repo.findAll();
    }

    public Product getProductDetailsById(int prodId){
        return repo.findById(prodId).get();
    }

    public Product updateProductDetails(int productId, Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return  repo.save(product);
    }

    public void deleteProduct(int prodcutId) {
        repo.deleteById(prodcutId);
    }
}
