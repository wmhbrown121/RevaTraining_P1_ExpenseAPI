package dev.hbrown.daotests;

import dev.hbrown.daos.ExpenseDAO;
import dev.hbrown.daos.ExpenseDaoHibernate;
import dev.hbrown.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTest {

    private static ExpenseDAO edao = new ExpenseDaoHibernate();
    private static Expense testExpense = null;

    @Test
    @Order(1)
    void create_expense(){
        Expense e1 = new Expense(0,100.00,"Client dinner",1_614_705_992,"Pending",0,1);
        edao.createExpense(e1);
        System.out.println(e1);
        testExpense = e1;
        Assertions.assertNotEquals(0,e1.getExpenseId());

    }

    @Test
    @Order(2)
    void get_all_expenses(){
        Expense e2 = new Expense(0,200.00,"Travel",1_614_705_000,"Pending",0,3);
        Expense e3 = new Expense(0,300.00,"Misc",1_614_704_000,"pending",1_614_705_992,1);
        Expense e4 = new Expense(0,400.00,"Travel",1_614_703_000,"approved",0,2);

        edao.createExpense(e2);
        edao.createExpense(e3);
        edao.createExpense(e4);

        Set<Expense> allExpenses = edao.getAllExpenses();
        Assertions.assertTrue(allExpenses.size()>3);
        System.out.println(allExpenses);
    }

    @Test
    @Order(3)
    void get_expense_by_id(){
        int id = testExpense.getExpenseId();
        Expense expense = edao.getExpenseById(id);

        Assertions.assertEquals(testExpense.getDateSubmitted(),expense.getDateSubmitted());
        System.out.println(expense);
    }

    @Test
    @Order(4)
    void update_expense(){
        Expense expense = edao.getExpenseById(testExpense.getExpenseId());
        System.out.println("UPDATE:");
        System.out.println("Before update: "+expense);
        expense.setAmount(2000);
        Expense exp = edao.updateExpense(expense);
        System.out.println("Update output: "+exp);

        Expense updatedExpense = edao.getExpenseById(testExpense.getExpenseId());
        Assertions.assertEquals(2000, updatedExpense.getAmount());
        System.out.println(updatedExpense);
    }

    @Test
    @Order(5)
    void delete_expense(){
        int id = testExpense.getExpenseId();
        boolean result = edao.deleteExpenseById(id);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(6)
    void get_expenses_by_status(){
        String status = "approved";
        Set<Expense> allExpenses = this.edao.getAllExpenses();
        Set<Expense> selectedExpenses = new HashSet<Expense>();
        for(Expense e : allExpenses){
            if(e.getStatus().equalsIgnoreCase(status)){
                selectedExpenses.add(e);
            }
        }
        Assertions.assertEquals(4,selectedExpenses.size());
        System.out.println(selectedExpenses);

    }

}
