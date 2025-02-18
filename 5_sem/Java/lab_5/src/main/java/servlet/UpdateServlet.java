package servlet;

import data.Dream;
import general.DatabaseManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Регистрация пользователя
public class UpdateServlet extends HttpServlet {
    Integer exitCode = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Читаем JSON из запроса
        StringBuilder jsonData = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonData.append(line);
        }

        // Преобразуем JSON в список объектов
        try {
            Class.forName("com.google.gson.Gson");
        } catch (ClassNotFoundException e) {
            exitCode = 1;
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        HttpSession session = request.getSession();
        List<Dream> data = gson.fromJson(jsonData.toString(), new TypeToken<List<Dream>>() {
        }.getType());
        // Обрабатываем данные (например, выводим в консоль)
        for (Dream dream : data) {
            System.out.println("Name: " + dream.getName() + ", Date: " + dream.getDate() + ", Quality: " + dream.getQuality() + ", Dream: " + dream.getDream() + ", Description: " + dream.getDescription());
        }
        try {
            DatabaseManager.updateDreams((Integer) session.getAttribute("user_id"), data);
        } catch (SQLException e) {
            exitCode = 1;
            throw new RuntimeException(e);
        }

        // Отправляем ответ клиенту
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(exitCode);
    }
}
