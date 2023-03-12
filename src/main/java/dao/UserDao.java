package dao;

import connection.ConnectionToDB;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements UserInt{
    private final String GET_ALL_USER = "from User";
    //private final String SELECT_USER_BY_ID = "select id from User where username like :USERNAME and password like :PASSWORD";
    private final String SELECT_USER_BY_ID = "select id from User where username like :USERNAME";

    /*
    Внесение пользователя в базу данных
     */
    @Override
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
    @Override
    public List<User> getAll() {
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<User> query = session.createQuery(GET_ALL_USER, User.class);
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
    @Override
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

    //public Integer getUserId(String username, String password){
    @Override
    public Integer getUserId(String username){
        Integer userId;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Integer> query = session.createQuery(SELECT_USER_BY_ID, Integer.class);
            query.setParameter("USERNAME", username);
            //query.setParameter("PASSWORD", password);
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
    @Override
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
