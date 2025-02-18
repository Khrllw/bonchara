package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class UpdateServlet extends HttpServlet {
    Integer exitCode = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Устанавливаем тип контента JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Создаем список данных
        Map<String, Integer> dataList = DatabaseManager.getCurrentDestemmerCrusherParams();
        assert dataList != null;
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getWinePressParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getFermentationTanksParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getChillingUnitParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getFilteringEquipmenParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getWinePumpParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getThermalShockTankParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getFiningEquipmentParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getBottlingLineParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getBottlingLineParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getTreatmentUnitParams()));
        dataList.putAll(Objects.requireNonNull(DatabaseManager.getElectromagneticTreatmentUnitParams()));

        // Сериализуем список в JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(dataList);

        // Отправляем JSON в ответе
        response.getWriter().write(jsonResponse);
    }
}