package com.snehakhanna.financemanager.service;

import java.util.List;

import com.snehakhanna.financemanager.model.Income;

public interface IncomeService {
    Income addIncome(Income income);

    List<Income> getAllIncomes();
}
