<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Включение сессий для отслеживания состояния пользователя -->
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <script src="js/login.js" type="text/javascript"></script>
</head>

<body>
<h1 id="header">Login</h1>

<!-- Форма авторизации -->
<form>
    <label for="username">Useranme</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="button" id="sign_in" onclick="sendFormData()">Sign in</button>
</form>

<!-- Форма регистрации -->
<form action="register.jsp">
    <button type="submit">Registration</button>
</form>

</body>
</html>