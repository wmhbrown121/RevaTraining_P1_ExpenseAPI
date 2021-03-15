package dev.hbrown.daos;

import dev.hbrown.entities.Expense;

import java.util.Set;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Set<Expense> getAllExpenses();
    Expense getExpenseById(int id);

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int id);

}
