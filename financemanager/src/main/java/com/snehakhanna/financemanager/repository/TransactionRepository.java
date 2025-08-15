package com.snehakhanna.financemanager.repository;

import com.snehakhanna.financemanager.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
