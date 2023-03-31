package com.iiex.lab7_lt.Repository;

import com.iiex.lab7_lt.Model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteProductById(int id);
}
