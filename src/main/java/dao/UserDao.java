package dao;

import connection.ConnectionToDB;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements UserInt{
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private final String GET_ALL_USER = "from User";
    //private final String SELECT_USER_BY_ID = "select id from User where username like :USERNAME and password like :PASSWORD";
    private final String SELECT_USER_BY_ID = "select id from User where username like :USERNAME";

    public UserDao() {
    }

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
            LOGGER.info("Registered user with the username: " + user.getUsername());
            result = true;

        } finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        LOGGER.info("registerUser - Transaction commit, session close");
        return result;
    }
    /*
    Метод для получения списка всех пользователей(в функционале приложения не используется)
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
            LOGGER.info("Received the user's name : " + user.getUsername() + " by its number " + id);
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
    Метод получения id пользователя по его логину
     */
    @Override
    public Integer getUserId(String username){
        Integer userId;
        Session session = null;
        try {
            session = ConnectionToDB.getSession();
            session.beginTransaction();
            Query<Integer> query = session.createQuery(SELECT_USER_BY_ID, Integer.class);
            query.setParameter("USERNAME", username);
            userId = query.uniqueResult();
            LOGGER.info("The user's number: " + userId + " was received by his name " + username);
        }
        finally {
            if (session != null) {
                session.getTransaction().commit();
            }
            if (session != null) {
                session.close();
            }
        }
        LOGGER.info("getUserId - Transaction commit, session close");
        return userId;
    }
    /*
    Метод для удаления из БД пользователя вместе с его задачами(в функционале приложения не используется)
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
