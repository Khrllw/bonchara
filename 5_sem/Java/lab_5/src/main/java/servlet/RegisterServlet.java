package servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import general.DatabaseManager;

import java.io.IOException;

// Регистрация пользователя
public class RegisterServlet extends HttpServlet {
    int registerResult;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        // Получение данных формы
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            // Запрос к БД
            registerResult = DatabaseManager.insertToUsers(username, password, email);
        } else {
            registerResult = 3;
        }
        // Отправка ответа сервера
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(registerResult);
    }
}
