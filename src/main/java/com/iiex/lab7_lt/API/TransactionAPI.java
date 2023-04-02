package com.iiex.lab7_lt.API;

import com.iiex.lab7_lt.Model.Transaction;
import com.iiex.lab7_lt.Model.Transaction;
import com.iiex.lab7_lt.Repository.TransactionRepository;
import com.iiex.lab7_lt.Repository.TransactionRepository;
import com.iiex.lab7_lt.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAPI {

  @Autowired
  TransactionRepository TransactionRepository;

  @Autowired
  private UserServiceImpl userService;

  @PutMapping("/{id}")
  public Transaction updateTransaction(
    @PathVariable int id,
    @RequestBody Transaction transactionData
  ) {
    Transaction transaction = userService.getTransaction();
    transaction.setMessage(transactionData.getMessage());
    transaction.setStatus(1);
    return TransactionRepository.saveAndFlush(transaction);
  }
}
