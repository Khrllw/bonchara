<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Включение сессий для отслеживания состояния пользователя -->
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <script src="js/login.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/body.css">
</head>

<body>

<h1 id="header">Login</h1><br>
<div class="form-wrap">
    <!-- Форма авторизации -->
    <form>
        <br><label for="username"></label><input type="text" id="username" name="username" placeholder="Username" required><br>

        <label for="password"></label><input type="password" id="password" name="password" placeholder="Password" required><br><br>

        <button type="button" id="sign_in"
                onclick="sendFormData()">Sign in
        </button>
    </form>
</div>
<!-- Форма регистрации -->
<form action="register.jsp">
    <button type="submit" style="background: none; width: max-content;">Not registered? Sign up</button>
</form>

</body>
</html>