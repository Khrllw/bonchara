package org.rip_rip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dreams";
    // "jdbc:mysql://localhost:3306;encrypt=true;database=dreams@localhost;trustServerCertificate=true;";
    //
    private static final String USER = "root";
    private static final String PASSWORD = "0000";


    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static boolean registerUser(String username, String password, String email) throws ClassNotFoundException {
        String query = "INSERT INTO Users (username, password_hash, email) VALUES (?, MD5(?), ?)";
        //Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public static Integer loginUser(String username, String password) {
        String query = "SELECT user_id FROM Users WHERE username = ? AND password_hash = MD5(?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("username");  // Возвращаем user_id, если пользователь найден
            } else {
                return null;  // Возвращаем null, если пользователя нет
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            return null;  // В случае ошибки возвращаем null
        }
    }

}
