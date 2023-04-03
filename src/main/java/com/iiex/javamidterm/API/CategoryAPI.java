package com.iiex.javamidterm.API;

import com.iiex.javamidterm.Model.Category;
import com.iiex.javamidterm.Repository.CategoryRepository;
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