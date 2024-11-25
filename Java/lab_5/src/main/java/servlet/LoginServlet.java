package servlet;

import general.DatabaseConnector;
import general.DatabaseManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Поиск записи в таблицу БД "Users"
public class LoginServlet extends HttpServlet {
    String exitMessage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.printf(username + ", " + password);
/*
        if (username != null && password != null) {
            // Проверяем, можно ли зарегистрировать нового пользователя
            boolean isRegistered = DatabaseManager.registerUser(username, password, email);

            if (isRegistered) {
                exitMessage = "<p style='color: green;'>Регистрация прошла успешно. Пожалуйста, войдите.</p>";
                // Перенаправляем пользователя на страницу входа после успешной регистрации
                //response.sendRedirect("index.jsp");
            } else {
                exitMessage = "<p style='color: red;'>Ошибка регистрации. Попробуйте еще раз.</p>";
            }
        }
*/
        exitMessage = "<p style='color: red;'>Ошибка регистрации. Попробуйте еще раз.</p>";
        response.setContentType("text/plain");

        OutputStream outStream = response.getOutputStream();
        outStream.write(exitMessage.getBytes(StandardCharsets.UTF_8));
        outStream.flush();
        outStream.close();
    }
}