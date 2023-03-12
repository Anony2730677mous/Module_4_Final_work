import connection.ConnectionToDB;
import dao.TodoDao;
import dao.UserDao;
import model.Todo;
import model.User;
import org.hibernate.Session;
import service.TodoService;
import service.UserService;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        //Session session = ConnectionToDB.getSession();
        //User user2 = new User("Petr", "Petrov", "petropivo", "00000");
        //User user3 = new User("dima", "Dmitriev", "diman", "12345");
        //session.beginTransaction();


       Date dateTask = new Date(2023, 5, 28);
//
        //Todo todo1 = new Todo("drink very hard", user2.getUsername(), "water drinking", dateTask, false);
        //Todo todo2 = new Todo("drinking", user2.getUsername(), "vodka drinking", dateTask, false);
//
//
        /*
        получаем данные пользователя из метода register сервлета UserController @WebServlet("/register")
         */
        String userName = "olvolk";
        String password = "112233";
        String firstname = "Oleg";
        String lastName = "Volkov";
        UserService userService = new UserService(new UserDao());
        //User user = userService.createNewUser(firstname, lastName,userName, password);

        TodoService todoService = new TodoService(new TodoDao(), new UserDao());
        String title = " buy drink";
        String description = "buy cola, coke, water";
        String username = userName;
        boolean isDone = false;
        Date date = dateTask;
        BigInteger todoId = BigInteger.valueOf(29);
        //Todo todo = todoService.createNewTodo(title, description, username, isDone, date);
        todoService.deleteTodo(todoId);

        System.out.println(todoService.todoList(userName));





          //UserDao userDao = new UserDao();
          //Integer id = userDao.getUserId(userName, password);
//          User user = userDao.getUserById(id);
          //boolean register = userDao.registerUser(user2);
        //TodoDao todoDao = new TodoDao();
        //boolean isDelete1 = todoDao.deleteTodo(BigInteger.valueOf(27));
        //boolean isUpdate = todoDao.updateTodo(todo1, BigInteger.valueOf(28));

          //System.out.println(isUpdate);
          //System.out.println(todoDao.insertTodo();
    }
}
