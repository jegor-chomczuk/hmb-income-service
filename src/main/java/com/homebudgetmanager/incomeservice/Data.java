package com.homebudgetmanager.incomeservice;

import com.homebudgetmanager.incomeservice.dao.Income;
import com.homebudgetmanager.incomeservice.enums.Month;
import com.homebudgetmanager.incomeservice.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Data {
    @Autowired
    private IncomeRepository incomeRepository;

    public void populate() {
//        incomeRepository.save(new Income("Salary", 10.00, Month.JANUARY, 2021, 1l));
//        incomeRepository.save(new Income("Partner's salary", 11.00, Month.MARCH, 2021, 1l));
//        incomeRepository.save(new Income("Bonus", 12.00, Month.JANUARY, 2021, 2l));
//        incomeRepository.save(new Income("Interest", 13.00, Month.JANUARY, 2022, 2l));
//        incomeRepository.save(new Income("Dividends", 14.00, Month.FEBRUARY, 2021, 1l));
//        incomeRepository.save(new Income("Sales", 15.00, Month.FEBRUARY, 2021, 1l));
//        incomeRepository.save(new Income("Tax refund", 16.00, Month.FEBRUARY, 2022, 2l));
    }
}
