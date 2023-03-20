package service;

import dao.UserDao;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService implements UserServiceInt{
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User createNewUser(String firstName, String lastName, String userName, String password){
        LOGGER.info("A user has been created with the name: " + userName);
        return new User(firstName, lastName, userName, password);
    }
    @Override
    public boolean isRegister(User user) throws ClassNotFoundException {
        boolean isRegistered = userDao.registerUser(user); // переменная создана для логирования
        LOGGER.info("The user with the name: " + user.getUsername() + " is registered? " + isRegistered);
        return isRegistered;
    }
}
