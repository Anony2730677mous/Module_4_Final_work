package servlets;

import dao.LoginDao;
import model.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;
    private LoginBean loginBean;

    public void init() {
        loginDao = new LoginDao();
        loginBean = new LoginBean();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}