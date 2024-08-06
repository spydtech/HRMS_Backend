package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.ErrorResponse;
import com.SpyDTech.HRMS.dto.ExpenseDto;
import com.SpyDTech.HRMS.dto.ExpenseRequest;
import com.SpyDTech.HRMS.entities.AllEmployees;
import com.SpyDTech.HRMS.entities.Expense;
import com.SpyDTech.HRMS.repository.AllEmployeeRepository;
import com.SpyDTech.HRMS.repository.ExpenseRepository;
import com.SpyDTech.HRMS.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    AllEmployeeRepository allEmployeeRepository;
    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public ResponseEntity save(ExpenseRequest expenseRequest) {
        Expense expense=new Expense();
        expense.setItem(expenseRequest.getItem());
        Optional<AllEmployees> employee=allEmployeeRepository.findByEmployeeId(expenseRequest.getOrderBy());
        if(employee.isPresent()) {
            expense.setOrderBy(employee.get());
        }else{
            ErrorResponse errorResponse = new ErrorResponse("employee not found");
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        expense.setFrom(expenseRequest.getFrom());
        expense.setDate(getFormattedDay(expenseRequest.getDate()));
        expense.setAmount(expenseRequest.getAmount());
        expense.setStatus(expenseRequest.getStatus());
        expense.setPaidBy(expenseRequest.getPaidBy());
        expenseRepository.save(expense);
        return ResponseEntity.ok(convertEntityToDto(expense));
    }

    public ExpenseDto convertEntityToDto(Expense expense){
        ExpenseDto expenseDto=new ExpenseDto();
        expenseDto.setId(expense.getId());
        expenseDto.setItem(expense.getItem());
        Optional<AllEmployees> employeesOptional=allEmployeeRepository.findById(Long.valueOf(expense.getOrderBy().getId()));
        expenseDto.setOrderBy(employeesOptional.get().getName());
        expenseDto.setFrom(expense.getFrom());
        expenseDto.setDate(expense.getDate());
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setStatus(expense.getStatus());
        expenseDto.setPaidBy(expense.getPaidBy());
        return expenseDto;
    }


    @Override
    public ResponseEntity getExpenses() {
        List<Expense> expenseList=expenseRepository.findAll();
        List<ExpenseDto> expenses=new ArrayList<>();
        for(Expense expense:expenseList){
            expenses.add(convertEntityToDto(expense));

        }
        return  ResponseEntity.ok(expenses);
    }

    @Override
    public ResponseEntity updateExpense(Integer id,ExpenseRequest expenseRequest) {
    Optional<Expense> optionalExpense=expenseRepository.findById(id);
    if(optionalExpense.isPresent()){
        Expense expense=optionalExpense.get();
        if(expenseRequest.getItem()!=null) {
            expense.setItem(expenseRequest.getItem());
        }
        if(expenseRequest.getOrderBy()!=null) {
            Optional<AllEmployees> employee = allEmployeeRepository.findByEmployeeId(expenseRequest.getOrderBy());
            if (employee.isPresent()) {
                expense.setOrderBy(employee.get());
            } else {
                ErrorResponse errorResponse = new ErrorResponse("employee not found");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            }
        }
        if(expenseRequest.getFrom()!=null){
        expense.setFrom(expenseRequest.getFrom());
        }
        if(expenseRequest.getDate()!=null) {
            expense.setDate(getFormattedDay(expenseRequest.getDate()));
        }
        if(expenseRequest.getAmount()!=null) {
            expense.setAmount(expenseRequest.getAmount());
        }
        if(expenseRequest.getStatus()!=null) {
            expense.setStatus(expenseRequest.getStatus());
        }
        if(expenseRequest.getPaidBy()!=null) {
            expense.setPaidBy(expenseRequest.getPaidBy());
        }
        expenseRepository.save(expense);
        return ResponseEntity.ok(convertEntityToDto(expense));
    }else {
        ErrorResponse errorResponse = new ErrorResponse("Expense Id  not found");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    }

    @Override
    public boolean deleteExpense(Integer expenseId) {
        if (expenseId != null && expenseRepository.existsById(expenseId)) {
            expenseRepository.deleteById(expenseId);
            return true;
        }
        return false;
    }

    private String getFormattedDay(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        return date.format(formatter);
    }
}
