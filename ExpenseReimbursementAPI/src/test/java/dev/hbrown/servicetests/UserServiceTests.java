package dev.hbrown.servicetests;

import dev.hbrown.daos.ExpenseDaoHibernate;
import dev.hbrown.daos.UserDaoHibernate;
import dev.hbrown.entities.Employee;
import dev.hbrown.entities.Manager;
import dev.hbrown.services.ExpenseService;
import dev.hbrown.services.ExpenseServiceImpl;
import dev.hbrown.services.UserService;
import dev.hbrown.services.UserServiceImpl;
import org.junit.jupiter.api.Test;

public class UserServiceTests {

    private static UserService userv = new UserServiceImpl(new UserDaoHibernate());

    @Test
    void get_user_by_username(){
        String username = "user1";
        String password = "pass1";
        Employee employee = userv.checkEmployeeCredentials(username,password);
        System.out.println(employee);
        Manager manager = userv.checkManagerCredentials(username,password);
        System.out.println(manager);

    }

}
