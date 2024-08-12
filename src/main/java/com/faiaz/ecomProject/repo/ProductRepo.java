package com.faiaz.ecomProject.repo;

import com.faiaz.ecomProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("SELECT p from Product p where " +
            "lower(p.name) like lower(concat('%', :keyword, '%')) or "+
            "lower(p.description) like lower(concat('%', :keyword, '%')) or "+
            "lower(p.brand) like lower(concat('%', :keyword, '%')) or "+
            "lower(p.category) like lower(concat('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
}
