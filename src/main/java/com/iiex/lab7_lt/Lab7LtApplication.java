package com.iiex.lab7_lt;

import com.iiex.lab7_lt.Repository.BrandRepository;
import com.iiex.lab7_lt.Repository.CategoryRepository;
import com.iiex.lab7_lt.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Lab7LtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab7LtApplication.class, args);
    }

}
