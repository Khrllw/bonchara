<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="general.DatabaseManager" %>

<%@ page session="true" %>
<!-- Включение сессий для отслеживания состояния пользователя -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
</head>
<body>
<script>
    function showInfoMessage(message) {
        if (message) {
            alert(message);
        }
    }
</script>
<h1>Login</h1>

<form action="index.jsp" method="post">
    <label for="username">Useranme</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Sign in</button>
</form>
<form action="register.jsp" method="post">
    <button type="submit">Registration</button>
</form>

<%
    // Обрабатываем отправленную форму
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && password != null) {
        // Получаем userId, если вход успешен
        Integer userId = DatabaseManager.loginUser(username, password);

// read script file

// call function from script file

        if (userId != null) {
            // Сохраняем user_id и username в сессии
            session.setAttribute("user_id", userId);
            session.setAttribute("username", username);


            // out.println("<p style='color: green;'>Welcome!</p>");

            //String message = "<p style='color: green;'>Welcome!</p>";
            response.sendRedirect("showDreams.jsp"); // Перенаправляем на страницу маршрутов
        } else {

            //inv.invokeFunction("showInfoMessage", "<p style='color: red;'>Incorrect username or password.</p>");


            //String message = "<p style='color: red;'>Incorrect username or password.</p>";
            //out.println("<p style='color: red;'>Incorrect username or password.</p>");
        }
    }
%>

</body>
</html>