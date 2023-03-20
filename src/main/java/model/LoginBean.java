package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/*
Класс для присвоения пользователю логина и пароля
 */
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(LoginBean.class);
    private String username;
    private String password;

    public String getUsername() {
        LOGGER.info("Method getUsername call has been made");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        LOGGER.info("Method setUsername call has been made");
    }

    public String getPassword() {
        LOGGER.info("Method getPassword call has been made");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        LOGGER.info("Method setPassword call has been made");
    }
}
