package com.iiex.lab7_lt.Repository;

import com.iiex.lab7_lt.Model.Color;
import com.iiex.lab7_lt.Model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteProductById(int id);
    List<Product> findByCategory_NameIgnoreCase(String category);

    List<Product> findByPriceGreaterThanEqual(Float price);

    List<Product> findByPriceLessThanEqual(Float price);

    List<Product> findByBrand_NameIgnoreCase(String brand);

}
