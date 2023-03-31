package com.iiex.lab7_lt.API;

import com.iiex.lab7_lt.Model.Brand;
import com.iiex.lab7_lt.Model.Category;
import com.iiex.lab7_lt.Repository.BrandRepository;
import com.iiex.lab7_lt.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryAPI {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/getAll")
    public List<Category> getItems() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }
    @GetMapping("/category/{id}")
    public Category addItem(@PathVariable("id") Integer id) {
        return  categoryRepository.findById(id).get();
    }
}