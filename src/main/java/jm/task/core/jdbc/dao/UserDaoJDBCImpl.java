package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(64)," +
                "last_name VARCHAR(64)," +
                "age TINYINT" +
                ");";

        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (PreparedStatement preparedStatement =
                     Util.getMySQLConnection().prepareStatement("DROP TABLE IF EXISTS users;")) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?);";

        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id =?;";

        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     Util.getMySQLConnection().prepareStatement("SELECT * FROM users;");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        try (PreparedStatement preparedStatement =
                     Util.getMySQLConnection().prepareStatement("DELETE FROM users;")) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
