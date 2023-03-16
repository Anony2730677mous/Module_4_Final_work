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
import java.math.BigInteger;


@WebServlet("/edit")
public class EditTodoServlet extends HttpServlet {
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
    В ответ пользователю метод перейдет на страницу с формой редактирования списка задач
    */
        HttpSession session = request.getSession(true);
        BigInteger id = BigInteger.valueOf(Long.parseLong(request.getParameter("id")));
        TodoService todoService = new TodoService(todoDAO, userDao);
        Todo existingTodo = todoService.getTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        session.setAttribute("todo", existingTodo);
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }
}
