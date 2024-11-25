<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, general.DatabaseManager"%>



<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>MyDreams</title>
</head>
<body>
<h1>Dreams User:</h1>
<table border="1">
    <thead>
    <tr>
        <th>Маршрут</th>
        <th>Описание</th>
        <th>Начало</th>
        <th>Конец</th>
        <th>Продолжительность (часы)</th>
        <th>Цена</th>
    </tr>
    </thead>
</table>
<script>
    function refreshRoutes() {
        location.reload(); // Обновление страницы
    }

    // Обновление каждые 10 секунд
    setInterval(refreshRoutes, 10 * 1000);
</script>
<form action="showDreams.jsp" method="post">
    <button type="submit">My dreams info</button>
</form>

<!-- Кнопка для выхода из системы -->
<form action="logout.jsp" method="post">
    <button type="submit">Exit</button>
</form>
</body>
</html>
