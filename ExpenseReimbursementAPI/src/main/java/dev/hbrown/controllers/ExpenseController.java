package dev.hbrown.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.hbrown.daos.ExpenseDaoHibernate;
import dev.hbrown.entities.Expense;
import dev.hbrown.services.ExpenseService;
import dev.hbrown.services.ExpenseServiceImpl;
import dev.hbrown.utils.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ExpenseController {

    private static Logger logger = Logger.getLogger(ExpenseController.class.getName()); // when the logger writes
    private ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoHibernate());
    Gson gson = new Gson();

    public Handler submitNewExpenseHandler = ctx -> {
        try{
            int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
            logger.info("Request started: Submit New Expense for Employee "+employeeId+".");

            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if (role.equals("employee")) {
                String body = ctx.body();
                Expense expense = gson.fromJson(body, Expense.class);
                expense.setEmployeeId(employeeId);
                this.expenseService.submitExpense(expense);
                String json = gson.toJson(expense);
                logger.info("Request completed: Expense submitted successfully");
                ctx.result(json);
                ctx.status(201);
            }else{
                logger.info("Request denied: invalid role.");
                ctx.result("Must be an EMPLOYEE to submit expenses");
                ctx.status(401);
            }

        }catch(Exception e){
            e.printStackTrace();
            logger.error("Exception thrown.",e);
            ctx.result("Token error");
            ctx.status(404);
        }
    };

    public Handler getAllExpensesHandler = ctx -> {

        try{
            logger.info("Request started: Get All Expenses");
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            String role = decodedJWT.getClaim("role").asString();
            if (role.equals("manager")) {
                Set<Expense> allExpenses = this.expenseService.getAllExpenses();
                String expensesJSON = gson.toJson(allExpenses);
                logger.info("Request completed: All expenses retrieved.");
                ctx.result(expensesJSON);
                ctx.status(201);
            }else if(role.equals("employee")){
                logger.info("Request denied: invalid role.");
                ctx.status(401);
                ctx.result("You are not authorized for this command");
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Exception thrown.",e);
            ctx.result("Token error");
            ctx.status(404);
        }
    };

    public Handler getExpensesByEmployeeIdHandler = ctx -> {
        try{
            int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
            logger.info("Request started: Get Expenses by Employee");
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            String role = decodedJWT.getClaim("role").asString();
            if(role.equalsIgnoreCase("manager")) {
                Set<Expense> expenses = this.expenseService.getExpensesByEmployee(employeeId);
                String expensesJSON = gson.toJson(expenses);
                logger.info("Request completed: Expenses retrieved successfully for Employee "+employeeId+" by Manager.");
                ctx.result(expensesJSON);
                ctx.status(201);
            }else if(role.equals("employee")){
                int id = decodedJWT.getClaim("id").asInt();
                if(employeeId==id) {
                    Set<Expense> expenses = this.expenseService.getExpensesByEmployee(employeeId);
                    String expensesJSON = gson.toJson(expenses);
                    logger.info("Request completed: Expenses retrieved successfully for Employee "+employeeId);
                    ctx.result(expensesJSON);
                    ctx.status(201);
                }else{
                    logger.info("Request denied: Invalid employee id.");
                    ctx.result("Invalid employee ID");
                    ctx.status(401);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            logger.error("Exception thrown.",e);
            ctx.result("Token error");
            ctx.status(404);
        }
    };

    public Handler getExpenseByIdHandler = ctx -> {
        try{
            int expenseId = Integer.parseInt(ctx.pathParam("expenseId"));
            logger.info("Request started: Get Expense by ID (Expense "+expenseId+").");
            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            String role = decodedJWT.getClaim("role").asString();
            Expense expense = this.expenseService.getExpenseById(expenseId);

            if(role.equalsIgnoreCase("manager")) {
                String expenseJSON = gson.toJson(expense);
                logger.info("Request completed: Expense "+expenseId+" retrieved.");
                ctx.result(expenseJSON);
                ctx.status(201);
            }else if(role.equals("employee")){
                int employeeId = decodedJWT.getClaim("id").asInt();
                if(expense.getEmployeeId()==employeeId){
                    String expenseJSON = gson.toJson(expense);
                    logger.info("Request completed: Expense "+expenseId+" retrieved.");
                    ctx.result(expenseJSON);
                    ctx.status(201);
                }else{
                    logger.info("Request denied: Invalid Expense ID ("+expenseId+") for Employee "+employeeId+".");
                    ctx.result("You do not have an expense with that ID");
                    ctx.status(401);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Exception thrown.",e);
            ctx.result("Token error");
            ctx.status(404);
        }
    };

    public Handler updateExpenseHandler = ctx -> {
        List validStatus = Arrays.asList("PENDING","APPROVED","DENIED");
        try{
            int id = Integer.parseInt(ctx.pathParam("expenseId"));
            logger.info("Request started: Update Expense (Expense "+id+").");

            String jwt = ctx.header("Authorization");
            DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
            String role = decodedJWT.getClaim("role").asString();

            if(role.equalsIgnoreCase("manager")){
                String body = ctx.body();
                Expense expense = gson.fromJson(body, Expense.class);
                if(validStatus.contains(expense.getStatus().toUpperCase())) {
                    expense.setExpenseId(id);
                    expense.setStatus(expense.getStatus().toUpperCase());
                    Expense updatedExpense = this.expenseService.updateExpense(expense);
                    String expenseJSON = gson.toJson(updatedExpense);
                    logger.info("Request completed: Expense ("+id+") successfully updated.");
                    ctx.result(expenseJSON);
                    ctx.status(200);
                }else {
                    logger.info("Request denied: Invalid string for status parameter.");
                    ctx.result("Invalid status update");
                    ctx.status(400);
                }
            }else if(role.equals("employee")){
                logger.info("Request denied: Employee attempted update.");
                ctx.result("You are not authorized to update expenses.");
                ctx.status(401);
            }

        }catch(Exception e){
            e.printStackTrace();
            logger.error("Exception thrown.",e);
            ctx.result("Token error");
            ctx.status(404);
        }
    };


}
