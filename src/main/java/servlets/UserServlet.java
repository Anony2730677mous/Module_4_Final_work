package servlets;

/*
Сервлет для обработки запросов на создание нового пользователя
 */

import dao.LoginDao;
import dao.UserDao;
import model.LoginBean;
import model.User;
import service.LoginService;
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
    private LoginDao loginDao;
    private LoginBean loginBean;

    public void init() {
        userDao = new UserDao();
        loginDao = new LoginDao();
        loginBean = new LoginBean();
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
        boolean isValidate;
        UserService userService = new UserService(userDao);
        LoginService loginService = new LoginService(loginDao, loginBean);
        isValidate = loginService.userValidate(username, password);
        if(!isValidate){
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
        }
        else {
            request.setAttribute("NOTIFICATION", "User already Registered! Please, try new username!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
    }
}
