package service;

import dao.UserDao;
import model.User;

public class UserService implements UserServiceInt{
    private final UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User createNewUser(String firstName, String lastName, String userName, String password){
        return new User(firstName, lastName, userName, password);
    }
    @Override
    public boolean isRegister(User user) throws ClassNotFoundException {
        return userDao.registerUser(user);
    }
}
