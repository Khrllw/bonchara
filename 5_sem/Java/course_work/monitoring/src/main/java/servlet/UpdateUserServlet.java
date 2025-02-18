package servlet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.User;
import db.DatabaseManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpSession;

// Регистрация или обновление данных пользователя
public class UpdateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.printf(String.valueOf(request) + "\n");
        // Устанавливаем тип контента ответа
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Читаем JSON из запроса
        StringBuilder jsonData = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonData.append(line);
        }
        System.out.printf("JS DATA: " + jsonData.toString() + "\n");

        // Преобразуем JSON в список объектов
        try {
            Class.forName("com.google.gson.Gson");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        User user = gson.fromJson(jsonData.toString(), new TypeToken<User>() {
        }.getType());
        System.out.printf(user.toString() + "\n");
        HttpSession session = request.getSession();

        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getAccessRights() != 0) {
            user.setAccessRights(1);
            user.setUserName(currentUser.getUserName());
        }
        // Обрабатываем данные (например, выводим в консоль)
        if (!user.getUserName().isEmpty() && !user.getPasswordHashed().isEmpty()) {
            // Запрос к БД
            DatabaseManager.insertToUsers(user);
        }
        //  }
        // Отправка ответа сервера
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter();
    }
}