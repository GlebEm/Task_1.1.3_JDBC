package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService { //  лассы dao/service должны реализовывать соответствующие интерфейсы
    UserDao userDao = new UserDaoJDBCImpl(); //создал UserDao, чтобы переиспользовать его методы


    // service переиспользует методы dao
    public void createUsersTable() {
        userDao.createUsersTable(); //service переиспользует методы dao
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();  //service переиспользует методы dao

    }

    public void saveUser(String name, String lastName, byte age) {
        //ѕосле каждого добавлени€ должен быть вывод в консоль ( User с именем Ц name добавлен в базу данных )
        userDao.saveUser(name, lastName, age); // service переиспользует методы dao
        System.out.println("User с именем Ц " + name + " добавлен в базу данных ");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);  //service переиспользует методы dao
    }

    public List<User> getAllUsers() {  //List<User> getAllUsers();
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }


    public void cleanUsersTable() {  // service переиспользует методы dao
        userDao.cleanUsersTable();
    }
}
