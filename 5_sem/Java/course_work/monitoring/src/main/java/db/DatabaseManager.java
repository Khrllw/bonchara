package db;

import com.google.gson.Gson;
import data.RecordLine;
import data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseManager {
    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = db.DatabaseConnector.connect();
            System.out.print("Try\n");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Integer> getCurrentDestemmerCrusherParams() {
        String query = "SELECT temperature, speed FROM DestemmerCrusherLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();

                dictionary.put("temperature1", result.getInt("temperature"));
                dictionary.put("speed1", result.getInt("speed"));
                return dictionary;

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

    public static Map<String, Integer> getWinePressParams() {
        String query = "SELECT temperature, pressure FROM WinePressLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();

                dictionary.put("temperature2", result.getInt("temperature"));
                dictionary.put("pressure2", result.getInt("pressure"));
                return dictionary;

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

    public static Map<String, Integer> getFermentationTanksParams() {
        String query = "SELECT volume, pressure, temperature FROM FermentationTanksLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("volume3", result.getInt("volume"));
                dictionary.put("temperature3", result.getInt("temperature"));
                dictionary.put("pressure3", result.getInt("pressure"));
                return dictionary;

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

    public static Map<String, Integer> getChillingUnitParams() {
        String query = "SELECT temperature, time FROM ChillingUnitLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("time4", result.getInt("time"));
                dictionary.put("temperature4", result.getInt("temperature"));
                return dictionary;

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

    public static Map<String, Integer> getFilteringEquipmenParams() {
        String query = "SELECT pressure, temperature FROM FilteringEquipmentLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("pressure5", result.getInt("pressure"));
                dictionary.put("temperature5", result.getInt("temperature"));
                return dictionary;

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

    public static Map<String, Integer> getWinePumpParams() {
        String query = "SELECT speed, pressure, temperature FROM WinePumpLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("speed6", result.getInt("speed"));
                dictionary.put("pressure6", result.getInt("pressure"));
                dictionary.put("temperature6", result.getInt("temperature"));
                return dictionary;

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

    public static Map<String, Integer> getThermalShockTankParams() {
        String query = "SELECT temperature, time FROM ThermalShockTankLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("time7", result.getInt("time"));
                dictionary.put("temperature7", result.getInt("temperature"));
                return dictionary;

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

    public static Map<String, Integer> getFiningEquipmentParams() {
        String query = "SELECT temperature, time FROM FiningEquipmentLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("time8", result.getInt("time"));
                dictionary.put("temperature8", result.getInt("temperature"));
                return dictionary;

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

    public static Map<String, Integer> getBottlingLineParams() {
        String query = "SELECT speed, pressure, temperature FROM BottlingLineLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("pressure9", result.getInt("pressure"));
                dictionary.put("speed9", result.getInt("speed"));
                dictionary.put("temperature9", result.getInt("temperature"));
                return dictionary;

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

    public static Map<String, Integer> getTreatmentUnitParams() {
        String query = "SELECT pressure, time FROM TreatmentUnitLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("pressure10", result.getInt("pressure"));
                dictionary.put("time10", result.getInt("time"));
                return dictionary;

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

    public static Map<String, Integer> getElectromagneticTreatmentUnitParams() {
        String query = "SELECT frequency, voltage, time FROM ElectromagneticTreatmentUnitLogs ORDER BY LogID DESC\n" +
                "LIMIT 1";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                Map<String, Integer> dictionary = new HashMap<>();
                dictionary.put("frequency11", result.getInt("frequency"));
                dictionary.put("voltage11", result.getInt("voltage"));
                dictionary.put("time11", result.getInt("time"));
                return dictionary;

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

    public static List<RecordLine> getGraph1() {
        String query = "SELECT Temperature, Speed FROM destemmercrusherlogs ORDER BY LogID DESC\n" +
                "LIMIT 20";

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            List<RecordLine> records = new ArrayList<>();
            while (result.next()) {

                int col1 = result.getInt("Temperature");
                int col2 = result.getInt("Speed");
                records.add(new RecordLine(col1, col2));

            }
            return records;
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // Удаление записи из таблицы БД "Users"
    public static void deleteUser(String username) throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM Users WHERE Username = " + username);
    }

    // Добавление новой записи в таблицу БД "Users"
    public static void insertToUsers(User newUser) {
        String query;
        // Проверяем зарегистрирован ли пользователь
        if (findUser(newUser.getUserName()) != null) {
            query = "UPDATE Users SET Username=?, PasswordHash = ?,  Rights = ?, LastName = ?, FirstName = ?, Patronymic = ? WHERE Username=" + newUser.getUserName();
        } else {
            // Шаблон запроса на добавление новой записи в таблицу БД "Users"
            query = "INSERT INTO Users (Username, PasswordHash, Rights, LastName, FirstName, Patronymic) VALUES (?, ?, ?, ?, ?, ?)";
        }

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newUser.getUserName());
            statement.setString(2, newUser.getPasswordHashed());
            statement.setInt(3, newUser.getAccessRights());
            statement.setString(4, newUser.getLastName());
            statement.setString(5, newUser.getFirstName());
            statement.setString(6, newUser.getPatronymic());
            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static User queryResultToUser(ResultSet result) throws SQLException {
        return new User(
                result.getString("Rights"),
                result.getString("Username"),
                result.getString("PasswordHash"),
                result.getString("LastName"),
                result.getString("FirstName"),
                result.getString("Patronymic")
        );
    }

    // Поиск записи в таблице БД "Users"
    public static User findUser(String username) {
        // Поиск записи в таблице БД "Users"
        String query = "SELECT * FROM Users WHERE Username = " + username;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            // Возвращает user_id, если пользователь найден
            if (result.next()) {
                return queryResultToUser(result);
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

}


