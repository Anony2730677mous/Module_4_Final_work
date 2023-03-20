package servlets;

import dao.TodoDao;
import dao.UserDao;
import model.Todo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/update")
public class UpdateTodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(UpdateTodoServlet.class);
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
        В ответ пользователю метод обновит задачу в БД и перейдет на страницу со списком задач
        */
        BigInteger id = BigInteger.valueOf(Long.parseLong(request.getParameter("id")));
        TodoService todoService = new TodoService(todoDAO, userDao);

        String username = request.getParameter("username");
        LOGGER.info("The user with the username: " + username + " edits the task with the number: " + id);
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date targetDate;
        try {
            targetDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));

        } catch (
                ParseException e) {
            throw new RuntimeException(e);
        }
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));

        Todo todo = todoService.createNewTodo(title, description, username, isDone, targetDate);
        todoService.updateTodo2(todo, id);


        response.sendRedirect("list");
    }
}