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

<h1 id="header">Login</h1>

<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/login.js" type="text/javascript"></script>

<form>
    <label for="username">Useranme</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit" id="sign_in">Sign in</button>
</form>
<form action="register.jsp">
    <button type="submit">Registration</button>
</form>
</body>
</html>