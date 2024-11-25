<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Интеграция JSP и jQuery/Servlet</title>
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>
    <script src="js/app-ajax.js" type="text/javascript"></script>
</head>
<body>
<p>Ваше имя : </p>
<label for="username"></label><input type="text" id="username"/><br/>
<span style="font-style:italic; font-size:75%">
               сервлет ответит после потери полем курсора</span>
<strong>Ответ сервлета </strong>:
<span id="ajaxUserServletResponse"></span>


</body>
</html>