package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    private static final String connectionURL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String userName = "root";
    private static final String password = "mysql";

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection(connectionURL, userName,
                password);
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.setProperty(Environment.URL, connectionURL);
                properties.setProperty(Environment.USER, userName);
                properties.setProperty(Environment.PASS, password);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.setProperty(Environment.HBM2DDL_AUTO, "none");
                properties.setProperty(Environment.SHOW_SQL, "true");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
