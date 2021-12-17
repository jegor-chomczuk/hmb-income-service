package com.homebudgetmanager.incomeservice.services;

import com.homebudgetmanager.incomeservice.dao.Income;
import com.homebudgetmanager.incomeservice.enums.Month;
import com.homebudgetmanager.incomeservice.interfeces.IIncomeService;
import com.homebudgetmanager.incomeservice.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService implements IIncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> filterIncomes(Long userId, int year, Optional<String> month, Optional<String> category){

        if (month.isPresent() && category.isPresent()){
            return incomeRepository.findByUserIdAndMonthAndCategoryAndYear(userId, Month.valueOf(month.get().toUpperCase()), category.get(), year);
        } else if (!month.isPresent() && category.isPresent()){
            return incomeRepository.findByUserIdAndCategoryAndYear(userId,category.get(), year);
        } else if (month.isPresent() && !category.isPresent()) {
            return incomeRepository.findByUserIdAndMonthAndYear(userId, Month.valueOf(month.get().toUpperCase()), year);
        } else {
            return incomeRepository.findByUserIdAndYear(userId, year);
        }
    }

    public void updateIncome(Long id, Income income){
        Optional<Income> storedExpense = incomeRepository.findById(id);
        if (storedExpense.isPresent()) {
            storedExpense.get().setCategory(income.getCategory());
            storedExpense.get().setComment(income.getComment());
            storedExpense.get().setDate(income.getDate());
            storedExpense.get().setAmount(income.getAmount());

            incomeRepository.save(storedExpense.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no expense with provided id.");
        }
    }
}
