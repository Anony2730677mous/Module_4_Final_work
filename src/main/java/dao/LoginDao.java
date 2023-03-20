package dao;

import connection.ConnectionToDB;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import model.LoginBean;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

public class LoginDao {
    private static final Logger LOGGER = LogManager.getLogger(LoginDao.class);
    private final String USER_VALIDATE = "from User where username like :USERNAME and password like :PASSWORD";

    public LoginDao() {
    }

    public boolean validate(LoginBean loginBean) {
        boolean status = false;
        Session session = null;
        String username = "";
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            username = loginBean.getUsername();
            String password = loginBean.getPassword();
            Query query = session.createQuery(USER_VALIDATE, User.class);
            query.setParameter("USERNAME", username);
            query.setParameter("PASSWORD", password);
            User user = (User) query.getSingleResult();
            if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
                LOGGER.info("login and/or password are suitable " + username);
                status = true;
            }
        } catch (PersistenceException e) {
            LOGGER.info("login and/or password are not suitable " + username);
            status = false;
        } finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        LOGGER.info("validate - Transaction commit, session close");
        return status;
    }
}
