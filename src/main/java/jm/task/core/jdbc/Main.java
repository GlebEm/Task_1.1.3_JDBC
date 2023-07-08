package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * В методе main класса Main должны происходить следующие операции:
 * Создание таблицы User(ов)
 * Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль
 * ( User с именем – name добавлен в базу данных )
 * Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
 * Очистка таблицы User(ов)
 * Удаление таблицы
 */

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {



        userService.createUsersTable();




        userService.saveUser("Патрик", "Бэйтман", (byte) 27);
        userService.saveUser("Майкл", "Бьюри", (byte) 31);
        userService.saveUser("Брюс", "Уэйн", (byte) 34);
        userService.saveUser("Тревор", "Резник", (byte) 41);



        userService.getAllUsers();

        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
