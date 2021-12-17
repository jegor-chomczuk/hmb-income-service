package com.homebudgetmanager.incomeservice.controller;

import com.homebudgetmanager.incomeservice.dao.Income;
import com.homebudgetmanager.incomeservice.enums.Month;
import com.homebudgetmanager.incomeservice.interfeces.IIncomeService;
import com.homebudgetmanager.incomeservice.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
//@Transactional
@RestController
@RequestMapping("/income/")
public class IncomeController {
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private IIncomeService incomeService;

    // GET Methods
    //  http://localhost:8300/income/all
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    //  http://localhost:8300/income/id/1
    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Income> getIncomeById(@PathVariable Long id) {
        return incomeRepository.findById(id);
    }

    //  http://localhost:8300/income/user-id/1/year/2021
    //  http://localhost:8300/income/user-id/1/year/2021/?month=january
    //  http://localhost:8300/income/user-id/1/year/2021/?category=Debt repayment
    //  http://localhost:8300/income/user-id/1/year/2021/?month=january&category=Debt repayment
    @GetMapping("user-id/{userId}/year/{year}")
    @ResponseStatus(HttpStatus.OK)
    public List<Income> getFilteredIncomes(@PathVariable Long userId, @PathVariable int year, @RequestParam Optional<String> month, @RequestParam Optional<String> category) {
        return incomeService.filterIncomes(userId, year, month, category);
    }

//    //  http://localhost:8300/income/user-id/1/year/2021
//    @GetMapping("user-id/{userId}/year/{year}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Income> getIncomeByUserIdAndYear(@PathVariable Long userId, @PathVariable int year) {
//        return incomeRepository.findByUserIdAndYear(userId, year);
//    }
//
//    //  http://localhost:8300/income/user-id/1/month/january/year/2021
//    @GetMapping("user-id/{userId}/month/{month}/year/{year}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Income> getIncomeByUserIdAndMonthAndYear(@PathVariable Long userId, @PathVariable String month, @PathVariable int year) {
//        return incomeRepository.findByUserIdAndMonthAndYear(userId, Month.valueOf(month.toUpperCase()), year);
//    }
//
//    //  http://localhost:8300/income/user-id/1/category/Debt repayment/year/2021
//    @GetMapping("user-id/{userId}/category/{category}/year/{year}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Income> getIncomeByUserIdAndCategoryAndYear(@PathVariable Long userId, @PathVariable String category, @PathVariable int year) {
//        return incomeRepository.findByUserIdAndCategoryAndYear(userId,category, year);
//    }
//
//    //  http://localhost:8300/income/user-id/1/category/Debt repayment/month/january/year/2021
//    @GetMapping("user-id/{userId}/category/{category}/month/{month}/year/{year}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Income> getIncomeByUserIdAndCategoryAndMonthAndYear(@PathVariable Long userId, @PathVariable String month, @PathVariable String category, @PathVariable int year) {
//        return incomeRepository.findByUserIdAndMonthAndCategoryAndYear(userId, Month.valueOf(month.toUpperCase()), category, year);
//    }

    //  DELETE Methods
    //  http://localhost:8300/income/delete/id/1
    @DeleteMapping("delete/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncomeById(@PathVariable Long id){
        incomeRepository.deleteById(id);
    }

    //  http://localhost:8300/income/delete/user-id/1
    @DeleteMapping("delete/user-id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncomesByUserId(@PathVariable Long id){
        incomeRepository.deleteByUserId(id);
    }

    //  http://localhost:8300/income/delete/category/Debt repayment
    @DeleteMapping("delete/category/{category}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncomesByCategory(@PathVariable String category){
        incomeRepository.deleteByCategory(category);
    }

    //  POST Method
    //  http://localhost:8300/income/add
    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addIncome(@RequestBody Income income){
        incomeRepository.save(income);
    }

    //  PUT Method
    //  http://localhost:8300/income/update/id/1
    @PutMapping("update/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateIncome(@PathVariable(name="id") Long id, @RequestBody Income income){
        incomeService.updateIncome(id, income);
    }
}