package dev.hbrown.daos;

import dev.hbrown.entities.Employee;
import dev.hbrown.entities.Manager;
import dev.hbrown.services.UserServiceImpl;
import dev.hbrown.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernate implements UserDAO {

    private static Logger logger = Logger.getLogger(UserDaoHibernate.class.getName()); // when the logger writes

    SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public Employee getEmployee(String username) {

        try {
            Session sess = sf.openSession();
            Query query = sess.createQuery("from Employee");
            List<Employee> employeeList = query.getResultList();

            sess.close();

            for(Employee employee : employeeList){
                if(employee.getUsername().equals(username)){
                    logger.info("Employee found");
                    return employee;
                }
            }
            logger.info("Employee NOT found");
            return null;

        }catch(HibernateException e){
            e.printStackTrace();
            logger.error("Exception Thrown at Employee DB.",e);
            return null;
        }
    }

    @Override
    public Manager getManager(String username) {

        try {
            Session sess = sf.openSession();

            Query query = sess.createQuery("from Manager");
            List<Manager> managerList = query.getResultList();

            sess.close();

            for(Manager manager : managerList){
                if(manager.getUsername().equals(username)){
                    logger.info("Manager found.");
                    return manager;
                }
            }
            logger.info("Manager NOT found");
            return null;

        }catch(HibernateException e){
            e.printStackTrace();
            logger.error("Exception Thrown at Manager DB.",e);
            return null;
        }
    }
}
