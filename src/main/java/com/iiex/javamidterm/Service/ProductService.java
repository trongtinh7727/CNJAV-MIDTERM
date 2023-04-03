package com.iiex.javamidterm.Service;

import com.iiex.javamidterm.Model.Color;
import com.iiex.javamidterm.Model.Product;
import com.iiex.javamidterm.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    public List<Product> filterProducts(String category, Double minPrice, Double maxPrice, String brand, String[] colors) {
        List<Product> products = productRepository.findAll();
        List<Product> productsCategory = productRepository.findAll();
        List<Product> productsMinPrice = productRepository.findAll();
        List<Product> productsMaxPrice = productRepository.findAll();
        List<Product> productsBrand = productRepository.findAll();

        if (category != null && !category.isEmpty()) {
            productsCategory = productRepository.findByCategory_Id(category);
        }
        if (minPrice != null) {
            productsMinPrice = productRepository.findByPriceGreaterThanEqual(minPrice);
        }
        if (maxPrice != null) {
            productsMaxPrice = productRepository.findByPriceLessThanEqual(maxPrice);
        }
        if (brand != null && !brand.isEmpty()) {
            productsBrand = productRepository.findByBrand_Id(brand);
        }
        products.retainAll(productsCategory);
        products.retainAll(productsMinPrice);
        products.retainAll(productsMaxPrice);
        products.retainAll(productsBrand);
        if (colors != null && colors.length > 0) {
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                for (Color color : product.getColors()) {
                    if (Arrays.stream(colors).anyMatch(c->{
                        if(Integer.parseInt(c) == color.getId()){
                            return  true;
                        }
                        return  false;
                    })) {
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
