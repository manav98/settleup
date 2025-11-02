package com.community.settleup.controller;

import com.community.settleup.entity.Expense;
import com.community.settleup.request.ExpenseRequest;
import com.community.settleup.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequest expenseRequest) {
        return new ResponseEntity<>(expenseService.createExpense(
                expenseRequest.getGroupId(),
                expenseRequest.getDescription(),
                expenseRequest.getAmount(),
                expenseRequest.getPaidByUserId(),
                expenseRequest.getParticipantIds()), HttpStatus.CREATED);
    }
}
