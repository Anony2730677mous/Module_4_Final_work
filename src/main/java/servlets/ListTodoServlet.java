package servlets;

import dao.TodoDao;
import dao.UserDao;
import model.Todo;
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
//        session.setAttribute("username", username);
//        session.setAttribute("listTodo", listTodo);
        request.setAttribute("listTodo", listTodo);
        request.setAttribute("username", username);


        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }
}