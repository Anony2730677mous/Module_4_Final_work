package service;

import dao.LoginDao;
import model.LoginBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {
    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

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
        LOGGER.info("The user with the name: " + userName + " in the database? " + isValidate);
        return isValidate;
    }
}
