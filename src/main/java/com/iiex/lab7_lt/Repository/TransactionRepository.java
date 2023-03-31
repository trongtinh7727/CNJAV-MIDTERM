package com.iiex.lab7_lt.Repository;

import com.iiex.lab7_lt.Model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    void deleteTransactionById(int id)
;}
