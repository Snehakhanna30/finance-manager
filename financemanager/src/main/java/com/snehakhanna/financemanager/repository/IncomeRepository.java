package com.snehakhanna.financemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snehakhanna.financemanager.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    // You can add custom queries here later
}
