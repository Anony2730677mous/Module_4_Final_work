package servlets;

import dao.TodoDao;
import dao.UserDao;

import service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;



@WebServlet("/delete")
public class DeleteTodoServlet extends HttpServlet {
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
        BigInteger id = BigInteger.valueOf(Long.parseLong(request.getParameter("id")));
        TodoService todoService = new TodoService(todoDAO, userDao);
        todoService.deleteTodo(id);

        response.sendRedirect("list");
    }
    }
