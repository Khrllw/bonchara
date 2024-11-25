<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, org.rip_rip.DatabaseHelper" %>
<%@ page session="true" %>  <!-- Включение сессий для отслеживания состояния пользователя -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<h1>Регистрация</h1>

<%
    // Обрабатываем отправленную форму
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");

    if (username != null && password != null && email != null) {
        // Проверяем, можно ли зарегистрировать нового пользователя
        boolean isRegistered = DatabaseHelper.registerUser(username, password, email);

        if (isRegistered) {
            out.println("<p style='color: green;'>Регистрация прошла успешно. Пожалуйста, войдите.</p>");
            // Перенаправляем пользователя на страницу входа после успешной регистрации
            response.sendRedirect("login.jsp");
        } else {
            out.println("<p style='color: red;'>Ошибка регистрации. Попробуйте еще раз.</p>");
        }
    }
%>

<form action="register.jsp" method="post">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <button type="submit">Зарегистрироваться</button>
</form>

</body>
</html>
