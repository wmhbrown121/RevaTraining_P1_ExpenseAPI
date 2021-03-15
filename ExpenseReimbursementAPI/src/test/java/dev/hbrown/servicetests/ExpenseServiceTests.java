package dev.hbrown.servicetests;


import dev.hbrown.daos.ExpenseDaoHibernate;
import dev.hbrown.entities.Expense;
import dev.hbrown.services.ExpenseService;
import dev.hbrown.services.ExpenseServiceImpl;
import org.junit.jupiter.api.*;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseServiceTests {

    private static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoHibernate());
    private static Expense testExpense = null;

    @Test
    @Order(1)
    void submit_new_expense(){

        Expense expense = new Expense(0,100.0,"travel",1600000000,"pending",0,1);
        expenseService.submitExpense(expense);
        System.out.println(expense);

        Assertions.assertNotEquals(0,expense.getExpenseId());
        testExpense = expense;
    }

    @Test
    @Order(2)
    void get_all_expenses(){
        Set<Expense> expenses = expenseService.getAllExpenses();

        System.out.println(expenses);
        Assertions.assertTrue(expenses.size()>3);
    }

    @Test
    @Order(3)
    void get_expense_by_id(){
        int id = testExpense.getExpenseId();
        Expense expense = expenseService.getExpenseById(id);
        Assertions.assertEquals(id,expense.getExpenseId());
        System.out.println(testExpense);
    }

    @Test
    @Order(4)
    void update_expense(){
        // Test to see if updating status updates the date verified
        int employeeId = testExpense.getEmployeeId();
        String newStatus = "APPROVED";
        String reasonApproved = "VALID";
        Expense newExpense = new Expense(testExpense.getExpenseId(), testExpense.getAmount(), reasonApproved, testExpense.getDateSubmitted(), newStatus, 0, employeeId);
        System.out.println(testExpense);
        Expense updatedExpense = expenseService.updateExpense(newExpense);
        System.out.println(newExpense);
        Assertions.assertNotEquals(0,updatedExpense.getDateVerified());
    }

    @Test
    @Order(5)
    void update_expense_(){
        // Test to see if updating status updates the date verified
        int expenseId = 4;
        String newStatus = "approved";
        Expense oldExpense = expenseService.getExpenseById(expenseId);
        oldExpense.setStatus(newStatus);
        Expense updatedExpense = expenseService.updateExpense(oldExpense);
        Assertions.assertTrue(updatedExpense.getStatus().equals("APPROVED"));
    }



}
