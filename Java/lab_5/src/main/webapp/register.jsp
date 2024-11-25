<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, general.DatabaseManager" %>
<%@ page import="general.DatabaseManager" %>
<%@ page session="true" %>  <!-- Включение сессий для отслеживания состояния пользователя -->
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Sign up</title>
</head>
<body>

<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
<script src="js/register.js" type="text/javascript"></script>

<h1>Registration</h1>

<form method="post">
  <label for="username">Username</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Password</label>
  <input type="password" id="password" name="password" required><br><br>

  <label for="email">Email</label>
  <input type="email" id="email" name="email" required><br><br>

  <button type="submit" id="sign_up">Sign up</button>
</form>

<form action="index.jsp">
  <button type="submit">Login</button>
</form>

</body>
</html>
