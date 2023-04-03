package com.iiex.javamidterm.Controller;

import com.iiex.javamidterm.Model.Order;
import com.iiex.javamidterm.Model.Product;
import com.iiex.javamidterm.Model.Transaction;
import com.iiex.javamidterm.Model.User;
import com.iiex.javamidterm.Repository.*;
import com.iiex.javamidterm.Service.impl.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @Autowired
  private ColorRepository colorRepository;

  @GetMapping("index")
  public String home(Model model) {
    List<Product> productList = productRepository.findAll();
    Transaction transaction = userService.getTransaction();
    String username = userService.getCurrentUsername();
    if (transaction.getOrders() != null) {
      model.addAttribute("orderCount", transaction.getOrders().size());
    } else {
      model.addAttribute("orderCount", 0);
    }
    model.addAttribute("username", username);
    model.addAttribute("prefix", "product");
    model.addAttribute("productList", productList);
    model.addAttribute("listCategory", categoryRepository.findAll());
    model.addAttribute("listColors", colorRepository.findAll());
    model.addAttribute("listBrand", brandRepository.findAll());
    return "customer/ecomerse";
  }

  @GetMapping("/")
  public String showproducts(Model model) {
    Transaction transaction = userService.getTransaction();
    List<Product> productList = productRepository.findAll();
    String username = userService.getCurrentUsername();
    model.addAttribute("username", username);
    if (transaction.getOrders() != null) {
      model.addAttribute("orderCount", transaction.getOrders().size());
    } else {
      model.addAttribute("orderCount", 0);
    }
    model.addAttribute("listColors", colorRepository.findAll());
    model.addAttribute("productList", productList);
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
    } else {
      model.addAttribute("orderCount", 0);
    }
    model.addAttribute("username", username);

    Product product = productRepository.findById(id).get();
    model.addAttribute("product", product);
    return "customer/product";
  }

  @GetMapping("/pricing")
  public String pricing(Model model) {
    Transaction transaction = userService.getTransaction();
    String username = userService.getCurrentUsername();
    User userAdress = userService.findByEmail(username);
    if (transaction.getOrders() != null) {
      model.addAttribute("orderCount", transaction.getOrders().size());
    } else {
      model.addAttribute("orderCount", 0);
    }
    model.addAttribute("transaction", transaction);
    model.addAttribute("userAdress", userAdress.getAddress());
    model.addAttribute("username", username);
    return "customer/pricing";
  }

  @GetMapping("/paymentsuccess")
  public String payment(Model model) {
    Transaction transaction = userService.getTransaction();
    transaction.setStatus(1);
    transactionRepository.saveAndFlush(transaction);
    String username = userService.getCurrentUsername();
    User userAdress = userService.findByEmail(username);
    if (transaction.getOrders() != null) {
      model.addAttribute("orderCount", transaction.getOrders().size());
    } else {
      model.addAttribute("orderCount", 0);
    }

    model.addAttribute("transaction", transaction);
    model.addAttribute("userAdress", userAdress.getAddress());
    model.addAttribute("username", username);
    return "customer/paymentsuccess";
  }

  @PostMapping("/addtocart")
  public String addToCart(@RequestParam(name = "id") String id, Model model) {
    Transaction transaction = userService.getTransaction();
    Product product = productRepository.findById(Integer.parseInt(id)).get();
    boolean flag = false;
    for (Order order : transaction.getOrders()) {
      if (order.getProduct().getId() == Integer.parseInt(id)) {
        order.setQuantity(order.getQuantity() + 1);
        order.setPrice(order.getQuantity() * order.getProduct().getPrice());
        flag = true;
        orderRepository.saveAndFlush(order);
        transaction.setAmount(
          transaction.getAmount() + order.getProduct().getPrice()
        );
        transactionRepository.saveAndFlush(transaction);
        break;
      }
    }
    if (flag == false) {
      Order order = new Order(-1, 1, product.getPrice(), product, transaction);
      orderRepository.saveAndFlush(order);
      transaction.setAmount(transaction.getAmount() + product.getPrice());
      transactionRepository.saveAndFlush(transaction);
    }
    return "redirect:/";
  }
}
