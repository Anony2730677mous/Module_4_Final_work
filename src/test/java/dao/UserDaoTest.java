package dao;


import model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    public void registerUserTest(){
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        User userMock = Mockito.mock(User.class);
        List<User> userList = new ArrayList<>();
        userList.add(userMock);
        try {
            Mockito.when(userDaoMock.registerUser(userMock)).thenReturn(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            assertEquals(userDaoMock.registerUser(userMock), true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllTest(){
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        User userMock = Mockito.mock(User.class);
        List<User> userList = new ArrayList<>();
        Mockito.when(userDaoMock.getAll()).thenReturn(userList);
        userList.add(userMock);
        assertNotEquals(userList, null);
    }

    @Test
    public void getUserByIdTest() {
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        User userMock = Mockito.mock(User.class);
        Integer userId = 1;
        Mockito.when(userDaoMock.getUserById(userId)).thenReturn(userMock);
        assertEquals(userDaoMock.getUserById(userId), userMock);
    }

    @Test
    public void getUserIdTest(){
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        String userName = "TestUserName";
        Integer userId = 1;
        Mockito.when(userDaoMock.getUserId(userName)).thenReturn(userId);
        assertEquals(userDaoMock.getUserId(userName), userId);
    }

    @Test
    public void removeUserTest() throws ClassNotFoundException {
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        User userMock1 = Mockito.mock(User.class);
        User userMock2 = Mockito.mock(User.class);
        List<User> userList = new ArrayList<>();
        userList.add(userMock1);
        userList.add(userMock2);
        Mockito.when(userDaoMock.removeUser(userMock1)).thenReturn(true);
        userList.remove(userMock1);
        assertNotEquals(userList.size(), 2);
    }

}