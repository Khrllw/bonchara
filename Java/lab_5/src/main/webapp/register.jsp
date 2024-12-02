<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Включение сессий для отслеживания состояния пользователя -->
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Sign up</title>
  <script src="js/register.js" type="text/javascript"></script>
</head>

<body>
<h1 id="header">Registration</h1>

<!-- Форма регистрации -->
<form>
  <label for="username">Username</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Password</label>
  <input type="password" id="password" name="password" required><br><br>

  <label for="email">Email</label>
  <input type="email" id="email" name="email" required><br><br>

  <button type="button" id="sign_up" onclick="sendFormData()">Sign up</button>
</form>

<!-- Форма авторизации -->
<form action="login.jsp">
  <button type="submit">Login</button>
</form>

</body>
</html>
