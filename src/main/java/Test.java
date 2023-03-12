import connection.ConnectionToDB;
import dao.TodoDao;
import dao.UserDao;
import model.Todo;
import model.User;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        //Session session = ConnectionToDB.getSession();
        User user2 = new User("Petr", "Petrov", "petropivo", "00000");
        //User user3 = new User("dima", "Dmitriev", "diman", "12345");
        //session.beginTransaction();


       Date dateTask = new Date(23, 4, 14);
//
        Todo todo1 = new Todo("drink very hard", user2.getUsername(), "water drinking", dateTask, false);
        //Todo todo2 = new Todo("drinking", user2.getUsername(), "vodka drinking", dateTask, false);
//
//
        String userName = "petropivo";
        String password = "00000";
          //UserDao userDao = new UserDao();
          //Integer id = userDao.getUserId(userName, password);
//          User user = userDao.getUserById(id);
          //boolean register = userDao.registerUser(user2);
        TodoDao todoDao = new TodoDao();
        //boolean isDelete1 = todoDao.deleteTodo(BigInteger.valueOf(27));
        boolean isUpdate = todoDao.updateTodo(todo1, BigInteger.valueOf(28));

          System.out.println(isUpdate);
          //System.out.println(todoDao.insertTodo();
    }
}
