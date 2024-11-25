package general;

import java.io.Closeable;
import java.sql.*;

public class DatabaseConnector implements Closeable {
    // Параметры подключения к базе данных
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dreams";
    private static final String ACCESS_USER = "root";
    private static final String ACCESS_PASSWORD = "0000";

    // Подключение к БД
    public static Connection connection;


    // Получение URL БД
    public static String getDbUrl() {
        return DB_URL;
    }

    // Получение логина root-пользоателя БД
    public static String getRoot() {
        return ACCESS_USER;
    }

    // Получение пароля root-пользоателя БД
    public static String getPassword() {
        return ACCESS_PASSWORD;
    }

    // Создание подключения к БД
    public Connection connect() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(DB_URL, ACCESS_USER, ACCESS_PASSWORD);
        }
        return connection;
    }

    // Удаления подключения к БД
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            connection = null;
        }
    }

}
