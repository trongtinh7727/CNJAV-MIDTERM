package com.iiex.javamidterm;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(com.iiex.javamidterm.Application.class, args);
    }

}
