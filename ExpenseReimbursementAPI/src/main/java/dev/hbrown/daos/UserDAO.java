package dev.hbrown.daos;

import dev.hbrown.entities.Employee;
import dev.hbrown.entities.Manager;

import java.util.Set;

public interface UserDAO {

    Employee getEmployee(String username);
    Manager getManager(String username);

}
