package dev.hbrown.daotests;

import dev.hbrown.daos.UserDAO;
import dev.hbrown.daos.UserDaoHibernate;
import dev.hbrown.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

    private UserDAO userDao = new UserDaoHibernate();

    @Test
    void get_employee_by_username(){
        String username = "user1";
        Employee employee = userDao.getEmployee(username);
        System.out.println(employee);
        String password = "pass1";
        Assertions.assertTrue(employee.getPassword().equals(password));
        System.out.println(employee);
        System.out.println(employee.getId());
        System.out.println(employee.getName());
        System.out.println(employee.getUsername());
        System.out.println(employee.getPassword());
    }
}
