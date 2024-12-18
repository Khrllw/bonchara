package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LogGenerator {
    public static Connection connection;
    static Random random = new Random();

    static {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            connection = db.DatabaseConnector.connect();
            System.out.print("Try\n");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void newLog() {
        // Период выполнения в миллисекундах (1 секунда = 1000 мс)
        final long interval = 1000;
        int entryCounter = 0;
        int entryMax = 50;
        while (true) {
            try {
                newDestemmerCrusherLog();
                winePressLog();
                fermentationTanksLog();
                chillingUnitLog();
                filteringEquipmentLog();
                winePumpLog();
                thermalShockTankLog();
                finingEquipmentLog();
                newBottlingLineLog();
                treatmentUnitLog();
                electromagneticTreatmentUnitLog();

                if (entryCounter == entryMax) {
                    entryCounter = 0;

                }
                entryCounter += 1;

                // Ждем 10 секунд
                Thread.sleep(interval);
            } catch (InterruptedException | SQLException e) {
                // Обрабатываем исключение, если поток был прерван
                System.out.println("Поток был прерван");
                break;
            }
        }
    }

    public static void newDestemmerCrusherLog() throws SQLException {
           (connection.createStatement()).executeUpdate("DELETE FROM DestemmerCrusherLogs\n" +
                    "ORDER BY logid\n" +
                   "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO DestemmerCrusherLogs (timelog, temperature, speed) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int temperature = random.nextInt(30 - 10 + 1) + 10;
        int speed = random.nextInt(200 - 50 + 1) + 50;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

            statement.setInt(2, temperature);
            statement.setInt(3, speed);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void newBottlingLineLog() throws SQLException {
       (connection.createStatement()).executeUpdate("DELETE FROM BottlingLinelogs\n" +
               "ORDER BY logid\n" +
                "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO BottlingLinelogs (timelog, speed, pressure, temperature) VALUES (?, ?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int temperature = random.nextInt(20 - 15 + 1) + 15;
        int speed = random.nextInt(5000 - 500 + 1) + 500;
        int pressure = random.nextInt(3 - 1 + 1) + 1;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(2, speed);
            statement.setInt(3, pressure);
            statement.setInt(4, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void chillingUnitLog() throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM ChillingUnitlogs\n" +
               "ORDER BY logid\n" +
               "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO  ChillingUnitlogs (timelog, temperature, time) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int temperature = random.nextInt(10 - 0 + 1);
        int time = random.nextInt(48 - 12 + 1) + 12;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(3, time);
            statement.setInt(2, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void electromagneticTreatmentUnitLog() throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM ElectromagneticTreatmentUnitLogs\n" +
               "ORDER BY logid\n" +
               "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO  ElectromagneticTreatmentUnitLogs (timelog, frequency, voltage, time) VALUES (?, ?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int frequency = random.nextInt(5000 - 500 + 1) + 500;
        int voltage = random.nextInt(5 - 1 + 1) + 1;
        int time = random.nextInt(10 - 1 + 1) + 10;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(2, frequency);
            statement.setInt(3, voltage);
            statement.setInt(4, time);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void fermentationTanksLog() throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM FermentationTanksLogs\n" +
              "ORDER BY logid\n" +
               "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO  FermentationTanksLogs (timelog, volume, pressure, temperature) VALUES (?, ?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int volume = random.nextInt(1000 - 50 + 1) + 50;
        int pressure = random.nextInt(5 - 1 + 1) + 1;
        int temperature = random.nextInt(30 - 12 + 1) + 12;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(2, volume);
            statement.setInt(3, pressure);
            statement.setInt(4, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void filteringEquipmentLog() throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM FilteringEquipmentLogs\n" +
                "ORDER BY logid\n" +
                "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO FilteringEquipmentLogs (timelog, pressure, temperature) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int pressure = random.nextInt(3 - 1 + 1) + 1;
        int temperature = random.nextInt(20 - 12 + 1) + 12;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(2, pressure);
            statement.setInt(3, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void finingEquipmentLog() throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM FiningEquipmentLogs\n" +
                "ORDER BY logid\n" +
                "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO FiningEquipmentLogs (timelog, temperature, time) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int time = random.nextInt(48 - 12 + 1) + 12;
        int temperature = random.nextInt(20 - 10 + 1) + 10;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(3, time);
            statement.setInt(2, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void treatmentUnitLog() throws SQLException {
        (connection.createStatement()).executeUpdate("DELETE FROM TreatmentUnitLogs\n" +
               "ORDER BY logid\n" +
               "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO TreatmentUnitLogs (timelog, pressure, time) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int time = random.nextInt(48 - 12 + 1) + 12;
        int pressure = random.nextInt(3 - 1 + 1) + 1;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(3, time);
            statement.setInt(2, pressure);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void thermalShockTankLog() throws SQLException {
       (connection.createStatement()).executeUpdate("DELETE FROM ThermalShockTankLogs\n" +
              "ORDER BY logid\n" +
            "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO ThermalShockTankLogs (timelog, temperature, time) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int time = random.nextInt(10 - 1 + 1) + 1;
        int temperature = random.nextInt(80 - 1 + 1) + 1;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(3, time);
            statement.setInt(2, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void winePressLog() throws SQLException {
       (connection.createStatement()).executeUpdate("DELETE FROM WinePressLogs\n" +
              "ORDER BY logid\n" +
             "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO WinePressLogs (timelog, pressure, temperature) VALUES (?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int pressure = random.nextInt(6 - 1 + 1) + 1;
        int temperature = random.nextInt(30 - 20 + 1) + 20;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(2, pressure);
            statement.setInt(3, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void winePumpLog() throws SQLException {
      (connection.createStatement()).executeUpdate("DELETE FROM WinePumpLogs\n" +
             "ORDER BY logid\n" +
               "LIMIT 1;");
        // Шаблон запроса на добавление новой записи в таблицу БД "Users"
        String query = "INSERT INTO WinePumpLogs (timelog, speed, pressure, temperature) VALUES (?, ?, ?, ?)";


        // Генерируем случайное число в диапазоне [min, max]
        int speed = random.nextInt(500 - 100 + 1) + 100;
        int pressure = random.nextInt(3 - 1 + 1) + 1;
        int temperature = random.nextInt(30 - 15 + 1) + 15;

        // Подготовка запроса
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            statement.setInt(2, speed);
            statement.setInt(3, pressure);
            statement.setInt(4, temperature);

            statement.executeUpdate();
        }
        // Вывод ошибки
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.print("print\n");
        newLog();
    }
}
