package com.iiex.lab7_lt.API;

import com.iiex.lab7_lt.Model.Brand;
import com.iiex.lab7_lt.Model.Category;
import com.iiex.lab7_lt.Model.Product;
import com.iiex.lab7_lt.Repository.CategoryRepository;
import com.iiex.lab7_lt.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductAPI {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/getAll")
    public List<Product> getItems() {
        List<Product> list = productRepository.findAll();
        return list;
    }
    @GetMapping("/product/{id}")
    public Product addItem(@PathVariable("id") Integer id) {
        return  productRepository.findById(id).get();
    }
    @GetMapping("/product/getbrand/{id}")
    public Brand getBrand(@PathVariable("id") Integer id) {
        return  productRepository.findById(id).get().getBrand();
    }
}

