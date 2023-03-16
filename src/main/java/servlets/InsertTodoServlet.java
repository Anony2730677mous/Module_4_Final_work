package servlets;

import dao.TodoDao;
import dao.UserDao;

import model.Todo;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;


@WebServlet("/insert")
public class InsertTodoServlet extends HttpServlet {
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
        HttpSession session = request.getSession(true);
        String title = request.getParameter("title");
        String username = String.valueOf(session.getAttribute("username"));
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Date date = new Date();
        TodoService todoService = new TodoService(todoDAO, userDao);
        Todo todo = todoService.createNewTodo(title, description, username, isDone, date);
        todoService.addNewTodo(todo);

        response.sendRedirect("list");
    }
}
