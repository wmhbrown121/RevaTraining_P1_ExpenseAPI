package dev.hbrown.servicetests;

import dev.hbrown.daos.ExpenseDAO;
import dev.hbrown.entities.Expense;
import dev.hbrown.services.ExpenseService;
import dev.hbrown.services.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class GetExpensesTests {

    @Mock
    ExpenseDAO expenseDAO = null;

    private ExpenseService expenseService = null;

    @BeforeEach
    void setup(){
        Expense exp1 = new Expense(1,100.00,"travel",1600000000,"PENDING",0,1);
        Expense exp2 = new Expense(2,200.00,"supplies",1600000000,"PENDING",0,2);
        Expense exp3 = new Expense(3,300.00,"travel",1600000000,"APPROVED", Instant.now().getEpochSecond(), 1);
        Expense exp4 = new Expense(4,400.00,"client dinner",1600000000,"PENDING",0,3);
        Expense exp5 = new Expense(5,500.00,"travel",1600050000,"PENDING",0,1);
        Expense exp6 = new Expense(6,600.00,"supplies",1600000000,"DENIED",Instant.now().getEpochSecond(),1);
        Set<Expense> expenseSet = new HashSet<Expense>();
        expenseSet.add(exp1);
        expenseSet.add(exp2);
        expenseSet.add(exp3);
        expenseSet.add(exp4);
        expenseSet.add(exp5);
        expenseSet.add(exp6);

        Mockito.when(expenseDAO.getAllExpenses()).thenReturn(expenseSet);
        this.expenseService = new ExpenseServiceImpl(this.expenseDAO);

    }

    @Test
    void get_expenses_by_employee(){
        Set<Expense> expenses = this.expenseService.getExpensesByEmployee(1);
        System.out.println(expenses);
        Assertions.assertEquals(4,expenses.size());
    }


}
