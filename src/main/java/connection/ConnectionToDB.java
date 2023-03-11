package connection;

import model.Todo;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionToDB {
    /*
    Настройки подключения к БД вынесены отдельно в виде hibernate.cfg.xml файла
     */
    private static final String configFile = "hibernate.cfg.xml";

    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(configFile);
            sessionFactory = configuration
                    .addAnnotatedClass(Todo.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }





}
