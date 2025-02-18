package servlet;

import db.DatabaseManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


// Авторизация пользователя
public class DeleteUserServlet extends HttpServlet {
    Integer exitCode = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        //Запрос к БД
        if (!username.isEmpty()) {
            try {
                DatabaseManager.deleteUser(username);
                exitCode = 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else exitCode = 1;

        // Отправка ответа сервера
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(exitCode);

    }
}