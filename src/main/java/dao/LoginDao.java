package dao;

import connection.ConnectionToDB;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import model.LoginBean;
import model.User;
import org.hibernate.Session;

public class LoginDao {
    private final String USER_VALIDATE = "from User where username like :USERNAME and password like :PASSWORD";


    public boolean validate(LoginBean loginBean){
        boolean status = false;
        Session session = null;
        try{
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            String username = loginBean.getUsername();
            String password = loginBean.getPassword();
            Query query = session.createQuery(USER_VALIDATE, User.class);
            query.setParameter("USERNAME", username);
            query.setParameter("PASSWORD", password);
            User user = (User) query.getSingleResult();
            if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                status = true;
            }
        }
        catch (PersistenceException e){
            status = false;
        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return status;
}
}
