package servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import general.DatabaseManager;
import jakarta.servlet.http.HttpSession;


// Авторизация пользователя
public class LoginServlet extends HttpServlet {
    Integer exitCode;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Запрос к БД
        Integer user_id = DatabaseManager.findUser(username, password);

        // Возвращает user_id, если пользователь найден
        if (user_id != null){
            // Объявление переменных сессии
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user_id);
            session.setAttribute("username", username);
            exitCode = 0;}
        else exitCode = 1;

        // Отправка ответа сервера
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(exitCode);
    }
}