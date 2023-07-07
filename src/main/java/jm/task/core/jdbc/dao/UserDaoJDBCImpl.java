package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработка всех исключений, связанных с работой с базой данных должна находиться в dao
 */
public class UserDaoJDBCImpl implements UserDao { // Классы dao/service должны реализовывать соответствующие интерфейсы
//    public static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() { // Класс dao должен иметь конструктор пустой/по умолчанию

    }
////Метод executeUpdate следует использовать, как для выполнения операторов управления данными типа
//// INSERT, UPDATE или DELETE (DML - Data Manipulation Language), так и для операторов определения
//// структуры базы данных CREATE TABLE, DROP TABLE (DDL - Data Definition Language).

    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS  users( 
                id SERIAL PRIMARY KEY  ,  --SERIAL - автоматический счетчик чтобы не определять следующий идентификатор. Аналогичен типу INT
                first_name VARCHAR(128) NOT NULL,
                last_name VARCHAR(128) NOT NULL,
                age INT  
                ); 
                """; //создаем ЕСЛИ не существует
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            //Connection connection1 = DriverManager.getConnection("db.url", "db.username", "db.password")) {
            // Statement statement = connection1.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы юзеров");
            e.printStackTrace();
        }
    }

    //Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users"); //удаляем ЕСЛИ существует
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // ? - знаки подстановки, вместо которых будут вставляться реальные значения.
        //try (Connection connection1 = DriverManager.getConnection(URL_KEY, USERNAME_KEY, PASSWORD_KEY)) {
        //   PreparedStatement preparedStatement = connection1.prepareStatement(sql);

        //prepared Statement позволяется защититься от SQL-инъекций и осуществить подстановку значений
        String sql = "INSERT INTO users (first_name, last_name, age) VALUES (?,?,?)";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate(); //не забываем выполнить

        } catch (SQLException e) {
            System.out.println("Проблемы с подключением (Упало при добавлении юзера)");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = " DELETE FROM users WHERE id=?";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Проблемы с подключением (удаление по ID)");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();  // service переиспользует методы dao
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) { // создаю объект Statement чтобы выполнить команду
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");// получение данных с помощью команды SELECT
            //Метод executeQuery используется в запросах, результатом которых является один единственный набор значений, таких как запросов типа SELECT.
            //Метод возвращает объект ResultSet, который содержит все полученные данные.
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));//   user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("first_name"));//  user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString("last_name")); //  user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte("age")); //user.setAge(resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";  //TRUNCATE — empty a table or set of tables
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
