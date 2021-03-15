package dev.hbrown.services;

import dev.hbrown.entities.Expense;

import java.util.Set;

public interface ExpenseService {

    Expense submitExpense(Expense expense);

    Set<Expense> getAllExpenses();
    Set<Expense> getExpensesByEmployee(int employeeId);
    Expense getExpenseById(int expenseId);

    Expense updateExpense(Expense expense);

}
