<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <form action="login.jsp" method="post">
        <button type="submit" >Войти</button>
    </form>
    <form action="register.jsp" method="post">
        <button type="submit" >Зарегистрироваться</button>
    </form>
</h1>
<br/>
</body>
</html>