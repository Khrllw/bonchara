package general;

import data.Dream;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {
    public static Connection connection;

    static {
        try {
            connection = DatabaseConnector.connect();
        } catch (SQLException e) {
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
        String query = "SELECT user_id FROM Users WHERE username = ? AND password = MD5(?)";

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
            Date date = resultSet.getDate("date");
            int quality = resultSet.getInt("quality");
            Boolean dream = resultSet.getBoolean("dream");
            String description = resultSet.getString("description");
            dreams.add(new Dream(name, date, quality, dream, description));

        }
        System.out.printf(dreams.toString());
        return dreams;
    }
/*
    // Добавление новой записи в таблицу БД "Dreams"
    public static Integer insertToDreams(String username, String password, String email) {

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
*/
}

/*
    public static List<Dream> getAllRoutes() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("JDBC Driver not found: " + e.getMessage());
//        }
        List<Dream> dreams = new ArrayList<>();
        String query = "select dream_id, date, duration, description, price where user_id = from Dreams";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Database connection successful!");

            while (rs.next()) {
                int routeId = rs.getInt("route_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String startLocation = rs.getString("start_location");
                String endLocation = rs.getString("end_location");
                int duration = rs.getInt("duration");
                double price = rs.getDouble("price");

                dreams.add(new Route(routeId, name, description, startLocation, endLocation, duration, price));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error");
        }
        return dreams;
    }

    public static void main(String[] args) {
        List<Route> routes = getAllRoutes();
        for (Route route : routes) {
            System.out.println(route.getName());
        }
    }
}*/



