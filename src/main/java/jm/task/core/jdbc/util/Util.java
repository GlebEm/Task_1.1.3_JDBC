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

    //    public static void main(String[] args) {
//
//        try (Connection connection = DriverManager.getConnection(
//                URL, PASSWORD, USERNAME
//        )) {
//            if (connection != null) {
//                System.out.println("Подключение к базе данных прошло успешно");
//            } else {
//                System.out.println("Неудачное подключение к базе данных");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e); //упало если не подключилось
//        }
//    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
  //          Class.forName(DRIVER);
 //           connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
            return  DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            ); //Возвращает connection, должно работать...
        } catch (SQLException   e) {
            System.out.println("Ошибка на этапе подключения");
            throw new RuntimeException(e);
        }
 //       return connection;
    }
}
//        if (connection.isEmpty()) {
//            try {
//                connection = DriverManager.getConnection(
//                                PropertiesUtil.get(URL_KEY),
//                                PropertiesUtil.get(USERNAME_KEY),
//                                PropertiesUtil.get(PASSWORD_KEY)
//                        ));
//            } catch (SQLException e) {
//                throw new RuntimeException(e); //если неудачное подключение - ошибка
//            }
//        }
//       return connection;
//    }
//}
// реализуйте настройку соеденения с БД
