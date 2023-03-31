package com.iiex.lab7_lt.Controller;

import com.iiex.lab7_lt.Model.Order;
import com.iiex.lab7_lt.Model.Product;
import com.iiex.lab7_lt.Model.Transaction;
import com.iiex.lab7_lt.Model.User;
import com.iiex.lab7_lt.Repository.*;
import com.iiex.lab7_lt.Service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BrandRepository brandRepository;
    @GetMapping("index")
    public String home(Model model){
        List<Product> productList = productRepository.findAll();
        Transaction transaction = userService.getTransaction();
        String username = userService.getCurrentUsername();
        if (transaction.getOrders() != null) {
            model.addAttribute("orderCount", transaction.getOrders().size());
        }
        else{
            model.addAttribute("orderCount", 0);
        }
        model.addAttribute("username",username);
        model.addAttribute("prefix", "product");
        model.addAttribute("productList", productList);
        model.addAttribute("listCategory", categoryRepository.findAll());
        model.addAttribute("listBrand", brandRepository.findAll());
        return "customer/ecomerse";
    }
    @GetMapping("/")
    public String showproducts(Model model) {
        Transaction transaction = userService.getTransaction();
        List<Product> productList = productRepository.findAll();
        String username = userService.getCurrentUsername();
        model.addAttribute("username",username);
        if (transaction.getOrders() != null) {
            model.addAttribute("orderCount", transaction.getOrders().size());
        }
        else{
            model.addAttribute("orderCount", 0);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("prefix", "product");
        model.addAttribute("listCategory", categoryRepository.findAll());
        model.addAttribute("listBrand", brandRepository.findAll());
        return "customer/ecomerse";
    }

    @GetMapping("/product")
    public String product(@RequestParam("id") Integer id, Model model) {
        Transaction transaction = userService.getTransaction();
        String username = userService.getCurrentUsername();
        if (transaction.getOrders() != null) {
            model.addAttribute("orderCount", transaction.getOrders().size());
        }
        else{
            model.addAttribute("orderCount", 0);
        }
        model.addAttribute("username",username);

        Product product= productRepository.findById(id).get();
        model.addAttribute("product",product);
        return "customer/product";
    }
    @GetMapping("/pricing")
    public String pricing( Model model) {
        Transaction transaction = userService.getTransaction();
        String username = userService.getCurrentUsername();
        if (transaction.getOrders() != null) {
            model.addAttribute("orderCount", transaction.getOrders().size());
        }
        else{
            model.addAttribute("orderCount", 0);
        }
        model.addAttribute("transaction",transaction);
        model.addAttribute("username",username);
        return "customer/pricing";
    }
    @PostMapping("/addtocart")
    public String addToCart(@RequestParam(name = "id") String id, Model model){
        Transaction transaction = userService.getTransaction();
        Product product = productRepository.findById(Integer.parseInt(id)).get();
        boolean flag = false;
        for (Order order:transaction.getOrders()) {
            if (order.getProduct().getId() == Integer.parseInt(id)) {
                order.setQuantity(order.getQuantity()+1);
                order.setPrice(order.getQuantity()*order.getProduct().getPrice());
                flag = true;
                orderRepository.saveAndFlush(order);
                transaction.setAmount(transaction.getAmount()+order.getProduct().getPrice());
                transactionRepository.saveAndFlush(transaction);
                break;
            }
        }
        if (flag == false) {
            Order order = new Order(-1,1,product.getPrice(),product,transaction);
            orderRepository.saveAndFlush(order);
            transaction.setAmount(transaction.getAmount()+product.getPrice());
            transactionRepository.saveAndFlush(transaction);
        }
        return "redirect:/";
    }
}