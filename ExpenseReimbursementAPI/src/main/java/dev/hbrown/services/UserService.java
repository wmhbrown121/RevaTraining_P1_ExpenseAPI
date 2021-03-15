package dev.hbrown.services;

import dev.hbrown.entities.Employee;
import dev.hbrown.entities.Manager;

public interface UserService {

    Employee checkEmployeeCredentials(String username, String password);

    Manager checkManagerCredentials(String username, String password);

}
