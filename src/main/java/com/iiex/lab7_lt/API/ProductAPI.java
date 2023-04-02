package com.iiex.lab7_lt.API;

import com.iiex.lab7_lt.Model.Brand;
import com.iiex.lab7_lt.Model.Category;
import com.iiex.lab7_lt.Model.Color;
import com.iiex.lab7_lt.Model.Product;
import com.iiex.lab7_lt.Repository.CategoryRepository;
import com.iiex.lab7_lt.Repository.ProductRepository;
import com.iiex.lab7_lt.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getItems() {
        List<Product> list = productRepository.findAll();
        return list;
    }
    @GetMapping("/{id}")
    public Product addItem(@PathVariable("id") Integer id) {
        return  productRepository.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productData) {
        return productRepository.findById(id)
                .map(product -> {
                    product.copy(productData);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam(required = false) String category,
                                            @RequestParam(required = false) Float minPrice,
                                            @RequestParam(required = false) Float maxPrice,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String[]  colors) {
        return productService.filterProducts(category, minPrice, maxPrice, brand, colors);
//        return  productRepository.findByCategoryAndPriceBetweenAndBrandAndColorsIn(category, minPrice, maxPrice, brand, colors);
    }

}