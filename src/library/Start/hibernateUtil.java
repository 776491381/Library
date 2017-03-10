package library.Start;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by FYY on 12/9/16.
 */
public class hibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;
    static{
        Configuration config = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        sessionFactory = config.buildSessionFactory(serviceRegistry);

    }

    public static SessionFactory getSessionFactory(){

        return sessionFactory;

    }


    public static Session getSession(){
        session = sessionFactory.openSession();
        return session;
    }

    public static void closesession(Session session){
        if(session!=null){
            session.close();
        }
    }


}
