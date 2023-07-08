package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * � ������ main ������ Main ������ ����������� ��������� ��������:
 * �������� ������� User(��)
 * ���������� 4 User(��) � ������� � ������� �� ���� �����. ����� ������� ���������� ������ ���� ����� � �������
 * ( User � ������ � name �������� � ���� ������ )
 * ��������� ���� User �� ���� � ����� � ������� ( ������ ���� ������������� toString � ������ User)
 * ������� ������� User(��)
 * �������� �������
 */

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {



        userService.createUsersTable();




        userService.saveUser("������", "�������", (byte) 27);
        userService.saveUser("�����", "�����", (byte) 31);
        userService.saveUser("����", "����", (byte) 34);
        userService.saveUser("������", "������", (byte) 41);



        userService.getAllUsers();

        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
