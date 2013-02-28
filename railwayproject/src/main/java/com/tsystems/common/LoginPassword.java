package com.tsystems.common;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/27/13
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPassword {

    public LoginPassword() {
    }

    public LoginPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
