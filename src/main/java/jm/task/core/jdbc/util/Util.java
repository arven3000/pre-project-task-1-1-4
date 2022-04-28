package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {
        String connectionURL = "jdbc:mysql://localhost:3306/new_schema";
        String userName = "root";
        String password = "mysql";
        Connection connection = DriverManager.getConnection(connectionURL, userName,
                password);
        return connection;
    }
}
