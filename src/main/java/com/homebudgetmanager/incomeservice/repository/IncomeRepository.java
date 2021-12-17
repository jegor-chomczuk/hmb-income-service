package com.homebudgetmanager.incomeservice.repository;

import com.homebudgetmanager.incomeservice.dao.Income;
import com.homebudgetmanager.incomeservice.enums.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserIdAndMonthAndCategoryAndYear(Long userId, Month month, String category, int year);
    List<Income> findByUserIdAndCategoryAndYear(Long userId, String category, int year);
    List<Income> findByUserIdAndMonthAndYear(Long userId, Month month, int year);
    List<Income> findByUserIdAndYear(Long userId, int year);
    void deleteByUserId(Long id);
    void deleteByCategory(String category);
}
