package dev.hbrown.services;

import dev.hbrown.daos.ExpenseDAO;
import dev.hbrown.entities.Expense;
import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ExpenseServiceImpl implements ExpenseService{

    private static Logger logger = Logger.getLogger(ExpenseServiceImpl.class.getName()); // when the logger writes

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) { this.expenseDAO = expenseDAO;}

    @Override
    public Expense submitExpense(Expense expense) {
        // New expenses always have the following conditions
        expense.setDateSubmitted(Instant.now().getEpochSecond());
        expense.setStatus("PENDING");
        expense.setDateVerified(0);
        return this.expenseDAO.createExpense(expense);
    }

    @Override
    public Set<Expense> getAllExpenses() { return this.expenseDAO.getAllExpenses(); }

    @Override
    public Set<Expense> getExpensesByEmployee(int employeeId) {
        Set<Expense> allExpenses = this.expenseDAO.getAllExpenses();
        Set<Expense> selectedExpenses = new HashSet<Expense>();
        for(Expense e : allExpenses){
            if(e.getEmployeeId()==employeeId){
                selectedExpenses.add(e);
            }
        }
        return selectedExpenses;
    }

        @Override
    public Expense getExpenseById(int expenseId) { return this.expenseDAO.getExpenseById(expenseId); }

    @Override
    public Expense updateExpense(Expense expense) {
        String newStatus = expense.getStatus().toUpperCase();
        Expense oldExpense = this.expenseDAO.getExpenseById(expense.getExpenseId());
        // If the status is changed, set verification_date to current time
        if(!oldExpense.getStatus().equalsIgnoreCase(expense.getStatus())) {
            expense.setDateVerified(Instant.now().getEpochSecond());
            expense.setReason("Reason for Expense:" + oldExpense.getReason() + "\nReason " + newStatus + ": " + expense.getReason());
        }
        expense.setEmployeeId(oldExpense.getEmployeeId());
        expense.setDateSubmitted(oldExpense.getDateSubmitted());

        return this.expenseDAO.updateExpense(expense);
    }

}
