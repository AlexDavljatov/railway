package com.tsystems.UI.security;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Simple Authenticator service, that checks user logins
 */
public class Authenticator {
    private static final Map<String, String> USERS = new HashMap<String, String>();

    static {
        USERS.put("demo", "demo");
    }

    public static boolean validate(String user, String password) {
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
