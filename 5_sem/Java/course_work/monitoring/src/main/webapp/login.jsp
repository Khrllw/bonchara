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
    <link rel="stylesheet" href="css/login.css">
</head>

<body>

<div id="header">
    <h1>Wine production</h1>
    <h1>monitoring system</h1>
</div>
<div id="content">
    <h2>Login</h2>
    <div class="form-wrap">
        <form>
            <br><label for="username"></label><input type="text" id="username" name="username" placeholder="Username"
                                                     required><br>

            <label for="password"></label><input type="password" id="password" name="password" placeholder="Password"
                                                 required><br><br>
            <button type="button" id="sign_in"
                    onclick="sendFormData()">Sign in
            </button>
        </form>
    </div>
</div>
</body>
</html>