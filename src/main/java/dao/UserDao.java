package dao;

import connection.ConnectionToDB;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    /*
    Внесение пользователя в базу данных
     */
    public boolean registerUser(User user) throws ClassNotFoundException {
        boolean result;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            session.persist(user);
            result = true;

        } finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
    /*
    Метод для получения списка всех пользователей
     */
    public List<User> getAll() {
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
    }
    /*
    Получение пользователя по id
     */
    public User getUserById(Integer id) {
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            return user;
        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
    }
    /*
    Метод получения id пользователя по его логину и паролю
     */
    public Integer getUserId(String username, String password){
        Integer userId;
        Session session = null;
        String hql = "select id from User where username like :USERNAME and password like :PASSWORD";
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("USERNAME", username);
            query.setParameter("PASSWORD", password);
            userId = query.uniqueResult();

        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return userId;
    }
    /*
    Метод для удаления из БД пользователя вместе с его задачами
    */
    public boolean removeUser(User user) throws ClassNotFoundException {
        boolean result;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            session.remove(user);
            result = true;

        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
