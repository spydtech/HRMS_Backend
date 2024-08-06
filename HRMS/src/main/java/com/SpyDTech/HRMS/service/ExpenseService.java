package com.SpyDTech.HRMS.service;

import com.SpyDTech.HRMS.dto.ExpenseRequest;
import org.springframework.http.ResponseEntity;

public interface ExpenseService {
    ResponseEntity save(ExpenseRequest expenseRequest);

    ResponseEntity getExpenses();

    ResponseEntity updateExpense(Integer id,ExpenseRequest expenseRequest);

    boolean deleteExpense(Integer expenseId);
}
