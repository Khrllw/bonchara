<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, general.DatabaseManager"%>
<%@ page import="data.Dream" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.ParseException" %>
<%@ page session="true"%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>MyDreams</title>
</head>
<body>
<h1>Dreams info</h1>
<h2>from <%=session.getAttribute("username")%></h2>
<table border="1">
    <thead>
    <tr>
        <th>Сон</th>
        <th>Дата</th>
        <th>Качество сна</th>
        <th>Сновидения</th>
        <th>Описание</th>
    </tr>
    </thead>
    <tbody>
        <%
            List<Dream> dreams = null;
            try {
                dreams = DatabaseManager.getDreams((Integer) session.getAttribute("user_id"));
            } catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }
            System.out.println("dreams = " + dreams);
        for (Dream dream : dreams) {
    %>
    <tr>
        <td><%= dream.getName() %></td>
        <td><%= dream.getDescription() %></td>
    <%}%>
    </tbody>
</table>
<form action="showDreams.jsp" method="post">
    <button type="submit">My dreams info</button>
</form>
<!--
<form method="post">
    <label for="username">Useranme</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="button" id="sign_in" onclick="sendFormData()">Sign in</button>
    <button type="submit">My dreams info</button>
</form>-->
<!-- Кнопка для выхода из системы -->
<form action="logout.jsp" method="post">
    <button type="submit">Exit</button>
</form>
</body>
</html>
