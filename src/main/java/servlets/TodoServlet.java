package servlets;

/*
Сервлет перенаправляет запросы на страницы login.jsp, todo-list.jsp, todo-form.jsp,
в зависимости от логики запроса после запроса данных, хранящихся в базе данных.
*/

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
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/")
public class TodoServlet extends HttpServlet {
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
        String action = request.getServletPath();
        /*
        формирование пути логики ответа,
        по которому пройдет пользователь, когда выберет то или иное действие на странице
        */
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTodo(request, response);
                    break;
                case "/delete":
                    deleteTodo(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateTodo(request, response);
                    break;
                case "/list":
                    listTodo(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    /*
    В ответ пользователю метод перейдет на страницу с формой заполнения списка задач
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }
    /*
    В ответ пользователю метод добавит новую задачу в список задач и в БД
     */
    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Date date = new Date();
        TodoService todoService = new TodoService(todoDAO, userDao);
        Todo todo = todoService.createNewTodo(title, description, username, isDone, date);
        todoService.addNewTodo(todo);

        response.sendRedirect("list");
    }
    /*
    В ответ пользователю метод удалит задачу из БД и из списка задач на его странице
     */
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        BigInteger id = BigInteger.valueOf(Long.parseLong(request.getParameter("id")));
        TodoService todoService = new TodoService(todoDAO, userDao);
        todoService.deleteTodo(id);

        response.sendRedirect("list");
    }
    /*
    В ответ пользователю метод перейдет на страницу с формой редактирования списка задач
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        BigInteger id = BigInteger.valueOf(Long.parseLong(request.getParameter("id")));
        TodoService todoService = new TodoService(todoDAO, userDao);
        Todo existingTodo = todoService.getTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");

        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }
    /*
    В ответ пользователю метод обновит задачу в БД и выдаст пользователю ответ об выполнении
     */
    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        BigInteger id = BigInteger.valueOf(Long.parseLong(request.getParameter("id")));
        TodoService todoService = new TodoService(todoDAO, userDao);

        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        Date targetDate;
        try {
            targetDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("targetDate")); // "dd/MM/yyyy было так
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));

        Todo todo = todoService.createNewTodo(title, description, username, isDone, targetDate);
        todoService.updateTodo2(todo, id);

        response.sendRedirect("list");
    }
    /*
    В ответ пользователю метод выдаст список всех задач, назначенных на него
     */
    private void listTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        TodoService todoService = new TodoService(todoDAO, userDao);

        List<Todo> listTodo = todoService.todoList(username);

        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }
}
