package servlets;

/*
Сервлет для обработки запросов на создание нового пользователя
 */

import dao.UserDao;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        response.sendRedirect("register/register.jsp");
    }

    /*
    Добавление нового пользователя в БД
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isRegister;
        UserService userService = new UserService(userDao);
        User user = userService.createNewUser(firstName, lastName, username, password);
        try {
            isRegister = userService.isRegister(user);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            if (isRegister) {
                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
    }
}
