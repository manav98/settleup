package com.community.settleup.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToMany
    @JoinTable(
            name = "expense_participants",
            joinColumns = @JoinColumn(name = "expense_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;
//    private LocalDateTime dateTime;
}
