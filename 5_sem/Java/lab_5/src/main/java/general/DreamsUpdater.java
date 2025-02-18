package general;

import data.Dream;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static general.DatabaseConnector.connection;

public class DreamsUpdater extends Thread {
    int user_id;
    Dream dream;

    DreamsUpdater(int user_id, Dream data) {
        this.user_id = user_id;
        this.dream = data;
    }

    @Override
    public void run() {
        if (!dream.getName().isEmpty()) {
            String query = "INSERT INTO Dreams (user, name, date, quality, dream, description) VALUES (?, ?, ?, ?,?,?)";

            // Подготовка запроса
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, user_id);
                statement.setString(2, dream.getName());
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = formatter.parse(dream.getDate());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                statement.setDate(3, sqlDate);
                statement.setInt(4, dream.getQuality());
                statement.setInt(5, (dream.getDream()? 1: 0));
                statement.setString(6, dream.getDescription());
                statement.executeUpdate();

            } catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }
}