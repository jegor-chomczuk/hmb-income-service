package com.homebudgetmanager.incomeservice.interfeces;

import com.homebudgetmanager.incomeservice.dao.Income;

import java.util.List;
import java.util.Optional;

public interface IIncomeService {
    List<Income> filterIncomes(Long userId, int year, Optional<String> month, Optional<String> category);
    void updateIncome(Long id, Income income);
}
