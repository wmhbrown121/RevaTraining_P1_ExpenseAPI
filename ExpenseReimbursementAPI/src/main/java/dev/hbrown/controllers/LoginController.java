package dev.hbrown.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.hbrown.daos.UserDaoHibernate;
import dev.hbrown.entities.Employee;
import dev.hbrown.entities.Expense;
import dev.hbrown.entities.Manager;
import dev.hbrown.services.ExpenseServiceImpl;
import dev.hbrown.services.UserService;
import dev.hbrown.services.UserServiceImpl;
import dev.hbrown.utils.JwtUtil;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class.getName()); // when the logger writes
    private UserService userService = new UserServiceImpl(new UserDaoHibernate());

    public Handler loginHandler = ctx -> {
        try{
            String body = ctx.body();
            Gson gson = new Gson();
            Employee credentials = gson.fromJson(body, Employee.class);
            String username = credentials.getUsername();
            String password = credentials.getPassword();
//        ctx.result(gson.toJson(credentials.getUsername()));

            logger.info("Start - New login request");
            Employee employee = this.userService.checkEmployeeCredentials(username, password);
            Manager manager = this.userService.checkManagerCredentials(username, password);
//        ctx.result(gson.toJson(employee));

            if (employee != null) {
                String jwt = JwtUtil.generate(employee.getId(), employee.getUsername(), "employee");
                logger.info("Employee login SUCCESSFUL");
//                ctx.cookie("Authorization",jwt);
                ctx.status(200);
//                ctx.result("employee");
                ctx.result(jwt);

            } else if (manager != null) {
                String jwt = JwtUtil.generate(manager.getId(), manager.getUsername(), "manager");
//                ctx.cookie("Authorization",jwt);
                logger.info("Manager login SUCCESSFUL");
                ctx.status(200);
//                ctx.result("manager");
                ctx.result(jwt);

            } else {
                logger.error("Login UNSUCCESSFUL. Invalid credentials.");
                ctx.result("Invalid credentials");
                ctx.status(401);
            }

        }catch(Exception e){
            e.printStackTrace();
            logger.error("Login UNSUCCESSFUL. Exception thrown.",e);
            ctx.result("Exception thrown");
            ctx.status(401);
        }

    };

    public Handler getRoleHandler = ctx -> {
        String jwt = ctx.header("Authorization");
        DecodedJWT decodedJWT = JwtUtil.isValidJWT(jwt);
        String role = decodedJWT.getClaim("role").asString();
        ctx.result(role);
    };

}
