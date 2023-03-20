package servlets;

import dao.TodoDao;
import dao.UserDao;
import model.Todo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(ListTodoServlet.class);
    private TodoDao todoDAO;
    private UserDao userDao;

    public void init() {
        todoDAO = new TodoDao();
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
    В ответ пользователю метод выдаст список всех задач, назначенных на него
     */
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        TodoService todoService = new TodoService(todoDAO, userDao);
        List<Todo> listTodo = todoService.todoList(username);
        LOGGER.info("The user with the username: " + username + " received the entire task list in size: " + listTodo.size());
        request.setAttribute("listTodo", listTodo);
        request.setAttribute("username", username);


        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }
}