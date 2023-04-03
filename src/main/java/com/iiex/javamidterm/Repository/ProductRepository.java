package com.iiex.javamidterm.Repository;

import com.iiex.javamidterm.Model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteProductById(int id);

    List<Product> findAllByBrand_NameAndCategory_NameAndPriceGreaterThanEqualAndPriceLessThanEqual(String brand,String category,Float minPrice,Float maxPrice);

    List<Product> findByCategory_Id(String category);

    List<Product> findByPriceGreaterThanEqual(Double price);

    List<Product> findByPriceLessThanEqual(Double price);

    List<Product> findByBrand_Id(String brand);

}
