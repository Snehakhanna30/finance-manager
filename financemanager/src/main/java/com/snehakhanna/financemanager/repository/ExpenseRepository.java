package com.snehakhanna.financemanager.repository;

import com.snehakhanna.financemanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
