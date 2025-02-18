<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Включение сессий для отслеживания состояния пользователя -->
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Sign up</title>
  <script src="js/register.js" type="text/javascript"></script>
  <link rel="stylesheet" href="css/reset.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/body.css">
</head>

<body>

<h1 id="header">Sign up</h1><br>
  <div class="form-wrap">
<!-- Форма регистрации -->
<form>
  <br><input type="text" id="username" name="username" placeholder="Username"><br>

  <label for="password"></label><input type="password" id="password" name="password" placeholder="Password" required><br>

  <label for="email"></label><input type="email" id="email" name="email" placeholder="Email" required><br><br>

  <button type="button" id="sign_up" onclick="sendFormData()">Sign up</button>
</form>
  </div>
<!-- Форма авторизации -->
<form action="login.jsp">
  <button type="submit"  style="background: none; width: max-content;">Registered? Login</button>
</form>

</body>
</html>
