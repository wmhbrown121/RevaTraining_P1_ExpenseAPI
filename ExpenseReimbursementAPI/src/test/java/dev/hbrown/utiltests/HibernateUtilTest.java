package dev.hbrown.utiltests;

import dev.hbrown.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class HibernateUtilTest {

    @Test
    public void creates_sessionfactory(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        System.out.println(sf);
    }

}
