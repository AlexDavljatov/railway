package com.tsystems.server.DB;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 2/19/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */

import java.sql.*;

public class ClassesJDBC {


    public static void main(String args[]) {
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://server:3306/db?user=root&password=matmex");
            Statement sql = con.createStatement();
            ResultSet result = sql.executeQuery("Select * from table");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
