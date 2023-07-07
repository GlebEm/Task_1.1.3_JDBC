package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ��������� ���� ����������, ��������� � ������� � ����� ������ ������ ���������� � dao
 */
public class UserDaoJDBCImpl implements UserDao { // ������ dao/service ������ ������������� ��������������� ����������
//    public static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() { // ����� dao ������ ����� ����������� ������/�� ���������

    }
////����� executeUpdate ������� ������������, ��� ��� ���������� ���������� ���������� ������� ����
//// INSERT, UPDATE ��� DELETE (DML - Data Manipulation Language), ��� � ��� ���������� �����������
//// ��������� ���� ������ CREATE TABLE, DROP TABLE (DDL - Data Definition Language).

    // �������� ������� ��� User(��) � �� ������ ��������� � ����������, ���� ����� ������� ��� ����������
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS  users( 
                id SERIAL PRIMARY KEY  ,  --SERIAL - �������������� ������� ����� �� ���������� ��������� �������������. ���������� ���� INT
                first_name VARCHAR(128) NOT NULL,
                last_name VARCHAR(128) NOT NULL,
                age INT  
                ); 
                """; //������� ���� �� ����������
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            //Connection connection1 = DriverManager.getConnection("db.url", "db.username", "db.password")) {
            // Statement statement = connection1.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("������ ��� �������� ������� ������");
            e.printStackTrace();
        }
    }

    //�������� ������� User(��) � �� ������ ��������� � ����������, ���� ������� �� ����������
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users"); //������� ���� ����������
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // ? - ����� �����������, ������ ������� ����� ����������� �������� ��������.
        //try (Connection connection1 = DriverManager.getConnection(URL_KEY, USERNAME_KEY, PASSWORD_KEY)) {
        //   PreparedStatement preparedStatement = connection1.prepareStatement(sql);

        //prepared Statement ����������� ���������� �� SQL-�������� � ����������� ����������� ��������
        String sql = "INSERT INTO users (first_name, last_name, age) VALUES (?,?,?)";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate(); //�� �������� ���������

        } catch (SQLException e) {
            System.out.println("�������� � ������������ (����� ��� ���������� �����)");
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
            System.out.println("�������� � ������������ (�������� �� ID)");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();  // service �������������� ������ dao
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) { // ������ ������ Statement ����� ��������� �������
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");// ��������� ������ � ������� ������� SELECT
            //����� executeQuery ������������ � ��������, ����������� ������� �������� ���� ������������ ����� ��������, ����� ��� �������� ���� SELECT.
            //����� ���������� ������ ResultSet, ������� �������� ��� ���������� ������.
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
        String sql = "TRUNCATE TABLE users";  //TRUNCATE � empty a table or set of tables
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
