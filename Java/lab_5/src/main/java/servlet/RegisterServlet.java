package servlet;

import general.DatabaseConnector;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Добавление новой записи в таблицу БД "Users"
public class RegisterServlet extends HttpServlet {
    String exitMessage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String query = "INSERT INTO Users (username, password, email) VALUES (?, MD5(?), ?)";

        try (DatabaseConnector database = new DatabaseConnector();
             Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            int rowsAffected = statement.executeUpdate();
            //return rowsAffected > 0;

        } catch (SQLException e) {
            exitMessage = "Error registering user: " + e.getMessage();
            // return false;
        }

        response.setContentType("text/plain");

        OutputStream outStream = response.getOutputStream();
        outStream.write(exitMessage.getBytes(StandardCharsets.UTF_8));
        outStream.flush();
        outStream.close();
    }
}
