package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();  //загрузили файлы в файл пропертис
    }

    private static void loadProperties() {  //загружаем в проперти файл
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {  //
            //закрываем поток в ресурсах трая
            PROPERTIES.load(inputStream); //загружаем в пропертис файл input stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    private PropertiesUtil() {
    }
}
