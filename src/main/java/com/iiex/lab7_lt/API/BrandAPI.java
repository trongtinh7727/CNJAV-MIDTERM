package com.iiex.lab7_lt.API;

import com.iiex.lab7_lt.Model.Brand;
import com.iiex.lab7_lt.Repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandAPI {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/brand/getAll")
    public List<Brand> getItems() {
        List<Brand> brandList = brandRepository.findAll();
        return brandList;
    }
    @GetMapping("/brand/{id}")
    public Brand addItem(@PathVariable("id") Integer id) {
        return  brandRepository.findById(id).get();
    }

    @PostMapping("/items")
    public Brand addItem(@RequestBody Brand item) {
        return brandRepository.save(item);
    }
}





