package com.SpyDTech.HRMS.controller;

import com.SpyDTech.HRMS.dto.ExpenseRequest;
import com.SpyDTech.HRMS.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/create/expense")
    public ResponseEntity saveExpense(@RequestBody ExpenseRequest expenseRequest){
        return ResponseEntity.ok(expenseService.save(expenseRequest));
    }
    @PutMapping("/edit/expense/{id}")
    public ResponseEntity updateExpense(@PathVariable Integer id,@RequestBody ExpenseRequest expenseRequest){
        return ResponseEntity.ok(expenseService.updateExpense(id,expenseRequest));
    }
    @DeleteMapping("/delete/expense/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer expenseId){
        boolean isRemoved = expenseService.deleteExpense(expenseId);
        if (isRemoved) {
            return new ResponseEntity<>("expense deleted successfully",  HttpStatus.OK);
        } else {
            return new ResponseEntity<>("expense not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get/expenses")
    public ResponseEntity getExpenses(){
        return ResponseEntity.ok(expenseService.getExpenses());
    }
}
