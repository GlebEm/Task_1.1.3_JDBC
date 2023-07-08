package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {


    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

//    db.url=jdbc:postgresql://localhost:5432/postgres
//    db.username=postgres
//    db.password=postgres


    public static Connection getConnection() {
        Connection connection = null;
        try {

            return  DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException   e) {
            System.out.println("Ошибка на этапе подключения");
            throw new RuntimeException(e);
        }
 //       return connection;
    }
}
