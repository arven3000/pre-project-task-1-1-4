package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("Ivan", "Medvedev", (byte) 22);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");

        User user2 = new User("Maria", "Ivanova", (byte) 19);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем – " + user2.getName() + " добавлен в базу данных");

        User user3 = new User("Georgy", "Volkov", (byte) 33);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем – " + user3.getName() + " добавлен в базу данных");

        User user4 = new User("Olga", "Smirnova", (byte) 41);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем – " + user4.getName() + " добавлен в базу данных");

        List<User> userList = userService.getAllUsers();
        System.out.println(userList);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
