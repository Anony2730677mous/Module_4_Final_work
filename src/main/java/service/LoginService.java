package service;

import dao.LoginDao;
import model.LoginBean;

public class LoginService {
    private final LoginDao loginDao;
    private final LoginBean loginBean;
    public LoginService(LoginDao loginDao, LoginBean loginBean){
        this.loginDao = loginDao;
        this.loginBean = loginBean;
    }

    public boolean userValidate(String userName, String password){
        boolean isValidate;
        loginBean.setUsername(userName);
        loginBean.setPassword(password);
        isValidate = loginDao.validate(loginBean);
        return isValidate;
    }
}
