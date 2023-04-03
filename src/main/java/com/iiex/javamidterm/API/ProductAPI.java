package com.iiex.javamidterm.API;

import com.iiex.javamidterm.Model.Product;
import com.iiex.javamidterm.Repository.ProductRepository;
import com.iiex.javamidterm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                                            @RequestParam(required = false) Double minPrice,
                                            @RequestParam(required = false) Double maxPrice,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String[]  colors) {
        return productService.filterProducts(category, minPrice, maxPrice, brand, colors);
//        return  productRepository.findByCategoryAndPriceBetweenAndBrandAndColorsIn(category, minPrice, maxPrice, brand, colors);
    }

}