package servlets;

/*
Сервлет для обработки параметров HTTP-запроса и перенаправления на соответствующую страницу JSP
на основе статуса входа в систему.
Если вход в систему успешно подтвержден с помощью базы данных, то перенаправление на страницу
"todo/todo-list.jsp", в противном случае перенаправление на страницу "login.jsp".
*/

import dao.LoginDao;
import model.LoginBean;
import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;
    private LoginBean loginBean;

    public void init() {
        loginDao = new LoginDao();
        loginBean = new LoginBean();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("login/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validate(request, response);
    }
    /*
   Метод для проверки логина и пароля пользователя
    */
    private void validate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*
        Получаем из запроса логин и пароль
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        /*
        Присваиваем логин и пароль пользователю и проверяем его наличие в БД
         */
        LoginService loginService = new LoginService(loginDao, loginBean);
        boolean isValidate = loginService.userValidate(username, password);
        /*
        Если пользователь существует, проходит перенаправление на страницу todo/todo-list.jsp
         */
        if (isValidate) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
            dispatcher.forward(request, response);
        } else {
        /*
        Если пользователя нет, то идет перенаправление на страницу login/login.jsp для регистрации или повторного ввода данных
         */
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("login/login.jsp");
        }
    }
}