package com.ubb.budgetwise_expenses.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record BudgetDto (
    String id,
    String name,
    Float amount,
    String createdAt,
    String userId,
    List<ExpenseDto> expenses
) { }
