package general;

import data.Dream;


import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;

public class DatabaseManager {
    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DatabaseConnector.connect();
            System.out.print("Try\n");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // Добавление новой записи в таблицу БД "Users"
    public static Integer insertToUsers(String username, String password, String email) {

        // Проверяем зарегистрирован ли пользователь
        if (findUser(username, password) == null) {

            // Шаблон запроса на добавление новой записи в таблицу БД "Users"
            String query = "INSERT INTO Users (username, password, email) VALUES (?, MD5(?), ?)";

            // Подготовка запроса
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, email);

                int rowsAffected = statement.executeUpdate();
                // Возвращает true, если запись успешно добавлена
                if (rowsAffected > 0) return 0;
                return 2;
            }
            // Вывод ошибки
            catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                return 2;
            }
        }
        return 1;
    }

    // Поиск записи в таблице БД "Users"
    public static Integer findUser(String username, String password) {

        // Шаблон поиска записи в таблице БД "Users"
        System.out.print("hey from finder\n");
        String query = "SELECT user_id FROM Users WHERE username = ? AND password = MD5(?)";
        System.out.printf(password, username);
        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                return result.getInt("user_id");
            } else {
                return null;
            }
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static List<Dream> getDreams(int userId) throws SQLException, ParseException {
        List<Dream> dreams = new ArrayList<>();
        // Шаблон поиска записи в таблице БД "Dreams"
        String query = "SELECT * FROM Dreams WHERE user = " + userId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String date = resultSet.getDate("date").toString();
            int quality = resultSet.getInt("quality");
            boolean dream = Boolean.TRUE.equals(resultSet.getBoolean("dream"));
            String description = resultSet.getString("description");
            dreams.add(new Dream(name, date, quality, dream, description));
        }
        System.out.printf(dreams.toString());
        return dreams;
    }

    // Обновление записей в таблице БД "Dreams"
    public static void updateDreams(int user_id, List<Dream> data) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "DELETE FROM dreams WHERE user = " + user_id;
        statement.executeUpdate(query);
        for (Dream dream : data) {
            new DreamsUpdater(user_id, dream).start();
        }
    }
}


