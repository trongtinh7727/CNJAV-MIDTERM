package com.iiex.javamidterm.API;

import com.iiex.javamidterm.Model.Order;
import com.iiex.javamidterm.Model.Transaction;
import com.iiex.javamidterm.Repository.OrderRepository;
import com.iiex.javamidterm.Repository.TransactionRepository;
import com.iiex.javamidterm.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  private UserServiceImpl userService;

  @GetMapping("/")
  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  @GetMapping("/{id}")
  public Order getItem(@PathVariable("id") Integer id) {
    return orderRepository.findById(id).get();
  }

  @PostMapping
  public Order createorder(@RequestBody Order order) {
    return orderRepository.save(order);
  }

  @PutMapping("/{id}")
  public Order updateOrder(@PathVariable int id, @RequestBody Order orderData) {
    Transaction transaction = userService.getTransaction();
    return orderRepository
      .findById(id)
      .map(order -> {
        transaction.setAmount(transaction.getAmount() - order.getPrice());
        order.setQuantity(orderData.getQuantity());
        order.setPrice(orderData.getPrice());
        transaction.setAmount(transaction.getAmount() + order.getPrice());
        transactionRepository.saveAndFlush(transaction);
        return orderRepository.saveAndFlush(order);
      })
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable int id) {
    Transaction transaction = userService.getTransaction();
    Order order = orderRepository.getById(id);
    transaction.setAmount(transaction.getAmount() - order.getPrice());
    transactionRepository.saveAndFlush(transaction);
    orderRepository.deleteById(id);
  }
}
