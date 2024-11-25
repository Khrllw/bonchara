package general;

import java.sql.*;

public class DatabaseManager {
    // Параметры подключения к базе данных
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dreams";
    private static final String ACCESS_USER = "root";
    private static final String ACCESS_PASSWORD = "0000";

    // Подключение к БД
    private static Connection connection;

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

    // Добавление новой записи в таблицу БД "Users"
    public static boolean registerUser(String username, String password, String email) {

        String query = "INSERT INTO Users (username, password, email) VALUES (?, MD5(?), ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, ACCESS_USER, ACCESS_PASSWORD);
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

    // Поиск записи в таблице БД "Users"
    public static Integer loginUser(String username, String password) {

        String query = "SELECT user_id FROM Users WHERE username = ? AND password = MD5(?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, ACCESS_USER, ACCESS_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt("user_id");
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
            return null;
        }
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

}

