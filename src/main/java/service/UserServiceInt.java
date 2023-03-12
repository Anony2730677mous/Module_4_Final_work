package service;


import model.User;

public interface UserServiceInt {
    User createNewUser(String firstName, String lastName, String userName, String password);
    boolean isRegister(User user) throws ClassNotFoundException;
}
