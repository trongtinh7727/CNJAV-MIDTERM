package com.iiex.lab7_lt.Service;

import com.iiex.lab7_lt.Model.Color;
import com.iiex.lab7_lt.Model.Product;
import com.iiex.lab7_lt.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> filterProducts(String category, Float minPrice, Float maxPrice, String brand, String[] colors) {
        List<Product> products = productRepository.findAll();
        if (category != null) {
            products = productRepository.findByCategory_NameIgnoreCase(category);
        }
        if (minPrice != null) {
            products = productRepository.findByPriceGreaterThanEqual(minPrice);
        }
        if (maxPrice != null) {
            products = productRepository.findByPriceLessThanEqual(maxPrice);
        }
        if (brand != null) {
            products = productRepository.findByBrand_NameIgnoreCase(brand);
        }
        if (colors != null && colors.length > 0) {
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                for (Color color : product.getColors()) {
                    if (Arrays.stream(colors).anyMatch(color.getName()::equalsIgnoreCase)) {
                        filteredProducts.add(product);
                        break;
                    }
                }
            }
            products = filteredProducts;
        }
        return products;
    }
}
