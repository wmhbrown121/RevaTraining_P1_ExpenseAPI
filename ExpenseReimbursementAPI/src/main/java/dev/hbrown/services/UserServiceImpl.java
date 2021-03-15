package dev.hbrown.services;

import dev.hbrown.daos.ExpenseDAO;
import dev.hbrown.daos.UserDAO;
import dev.hbrown.entities.Employee;
import dev.hbrown.entities.Expense;
import dev.hbrown.entities.Manager;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService{

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName()); // when the logger writes

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO){ this.userDAO = userDAO;}

    @Override
    public Employee checkEmployeeCredentials(String username, String password) {

        try {
            logger.info("Accessing Employee DB..");
            Employee employee = this.userDAO.getEmployee(username);

            if(employee!=null){
                if(employee.getPassword().equals(password)){
                    logger.info("Employee password verified.");
                    return employee;
                }
            }
            return null;
        }catch(Exception e){
            logger.error("Exception Thrown: ",e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Manager checkManagerCredentials(String username, String password) {
        try{
            logger.info("Accessing Manager DB...");
            Manager manager = this.userDAO.getManager(username);
            if(manager!=null){
                if(manager.getPassword().equals(password)){
                    logger.info("Manager password verified.");
                    return manager;
                }
            }

            return null;
        }catch (Exception e){
            logger.error("Exception Thrown: ",e);
            e.printStackTrace();
            return null;
        }
    }
}
