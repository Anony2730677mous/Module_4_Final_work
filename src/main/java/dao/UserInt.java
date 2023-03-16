package dao;

import model.User;
import java.util.List;

public interface UserInt {
    boolean registerUser(User user) throws ClassNotFoundException;
    List<User> getAll();
    User getUserById(Integer id);
    Integer getUserId(String username);
    boolean removeUser(User user) throws ClassNotFoundException;
}
