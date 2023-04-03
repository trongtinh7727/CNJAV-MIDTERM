package com.iiex.javamidterm.Repository;

import com.iiex.javamidterm.Model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    void deleteTransactionById(int id)
;}
