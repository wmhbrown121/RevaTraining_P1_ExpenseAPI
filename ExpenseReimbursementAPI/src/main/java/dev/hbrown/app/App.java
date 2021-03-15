package dev.hbrown.app;

import dev.hbrown.controllers.ExpenseController;
import dev.hbrown.controllers.LoginController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                }
        ).start();

        ExpenseController expenseController = new ExpenseController();
        LoginController loginController = new LoginController();

        app.post("/expenses/employee/:employeeId",expenseController.submitNewExpenseHandler);
        app.get("/expenses",expenseController.getAllExpensesHandler);
        app.get("/expenses/employee/:employeeId",expenseController.getExpensesByEmployeeIdHandler);
        app.get("/expenses/:expenseId",expenseController.getExpenseByIdHandler);
        app.put("/expenses/:expenseId",expenseController.updateExpenseHandler);

        app.post("/users/login",loginController.loginHandler);
        app.get("/users/login/role",loginController.getRoleHandler);
    }

}
