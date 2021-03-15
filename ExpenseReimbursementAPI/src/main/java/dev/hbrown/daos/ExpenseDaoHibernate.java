package dev.hbrown.daos;

import dev.hbrown.controllers.LoginController;
import dev.hbrown.entities.Expense;
import dev.hbrown.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ExpenseDaoHibernate implements ExpenseDAO{

    private static Logger logger = Logger.getLogger(ExpenseDaoHibernate.class.getName()); // when the logger writes

    private SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public Expense createExpense(Expense expense) {
        try{
            Session sess = sf.openSession();
            sess.getTransaction().begin();
            sess.save(expense);
            sess.getTransaction().commit();
            sess.close();
            logger.info("Expense persisted in DB.");
            return expense;
        }catch(Exception e){
            logger.error("Expense NOT persisted.",e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Expense> getAllExpenses() {
        try{
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session sess = sf.openSession();
            String hql = "FROM Expense";
            Query query = sess.createQuery(hql);
            List<Expense> expenseList = query.list();
            Set<Expense> expenseSet = new HashSet<Expense>(expenseList);
            logger.info("All Expenses retrieved from DB.");
            return expenseSet;
        }catch(Exception e){
            logger.error("Expenses NOT retrieved from DB.");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Expense getExpenseById(int id) {
        try{
            Session sess = sf.openSession();
            Expense exp = sess.get(Expense.class, id);
            sess.close();
            logger.info("Expense "+id+" retrieved from DB.");
            return exp;
        }catch(Exception e){
            logger.error("Expense "+id+" NOT retrieved from DB.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try {
            expense.setStatus(expense.getStatus().toUpperCase());
            Session sess = sf.openSession();
            sess.getTransaction().begin();
            sess.update(expense);
            sess.getTransaction().commit();
            sess.close();
            logger.info("Expense updated in DB successfully.");
            return expense;
        }catch(Exception e){
            logger.error("Expense NOT updated in DB.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpenseById(int id) {
        try{
            Session sess = sf.openSession();
            sess.getTransaction().begin();
            sess.delete(this.getExpenseById(id));
            sess.getTransaction().commit();
            sess.close();
            logger.info("Expense "+id+" was deleted from DB successfully.");
            return true;
        }catch(HibernateException he){
            logger.info("Expense "+id+" was NOT deleted from DB.",he);
            he.printStackTrace();
            return false;
        }
    }
}
