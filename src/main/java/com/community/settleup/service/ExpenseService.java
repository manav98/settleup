package com.community.settleup.service;

import com.community.settleup.entity.Expense;
import com.community.settleup.entity.Group;
import com.community.settleup.entity.User;
import com.community.settleup.repository.ExpenseRepository;
import com.community.settleup.repository.GroupRepository;
import com.community.settleup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;


    public Expense createExpense(Long groupId, String description, Double amount, Long paidByUserId, List<Long> participantIds) {
        Group currentGroup = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User paidByUser = userRepository.findById(paidByUserId).orElseThrow(() -> new RuntimeException("User not found"));
        List<User> participatingUsers = userRepository.findAllById(participantIds);
        Expense newExpense = Expense.builder()
                .group(currentGroup)
                .description(description)
                .amount(amount)
                .paidBy(paidByUser)
                .participants(participatingUsers)
                .build();
        Expense savedExpense = expenseRepository.save(newExpense);
        splitExpense(paidByUser, participatingUsers, amount);
        return savedExpense;
    }

    private void splitExpense(User paidByUser, List<User> participatingUsers, Double amount) {
        Double sharePerUser = amount / (participatingUsers.size() + 1);
        paidByUser.setBalance(paidByUser.getBalance() + (amount - sharePerUser));
        userRepository.save(paidByUser);
        for (User user : participatingUsers) {
            user.setBalance(user.getBalance() - sharePerUser);
            userRepository.save(user);
        }
    }
}
