package servlet;

import com.google.protobuf.Value;
import general.DatabaseManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Регистрация пользователя
public class NewEntryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        int registerResult;
        // Получение данных формы
        HttpSession session = request.getSession();
        int user_id = Integer.parseInt((String) session.getAttribute("user_id"));
        String password = request.getParameter("");
        String email = request.getParameter("email");

        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            // Запрос к БД
            registerResult = DatabaseManager.insertToDreams(user_id, );
        } else {
            registerResult = 3;
        }
        // Отправка ответа сервера
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(registerResult);
    }
}
