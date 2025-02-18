<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, general.DatabaseManager" %>
<%@ page import="data.Dream" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.ParseException" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>MyDreams</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/body.css">
</head>
<body style="color: white; justify-content: start;">

<div class="account-header">
    <div style="width: 30%">
        <h2>User: <%=session.getAttribute("username")%>
        </h2>
    </div>
    <!-- Кнопка для выхода из системы -->
    <div style="width: 30%">
        <button style="background: none; color: white; padding: 10px;" id="update-button">Update</button>
    </div>
    <div style="width: 30%">
        <!-- Кнопка для выхода из системы -->
        <form action="logout.jsp" method="post">
            <button type="submit" style="background: white; color: midnightblue;">Exit</button>
        </form>
    </div>
</div>
<h1>Dreams info</h1>
<table id="editableTable">
    <thead>
    <tr>
        <th>Title</th>
        <th>Date</th>
        <th>Sleep quality</th>
        <th>Dreams</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody id="editableTable-tbody">
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

        <td>
            <label>
                <input type="text" placeholder="EMPTY" value="<%= dream.getName()%>" class="table-input">
            </label>
        </td>
        <td>
            <label>
                <input type="date" data-placeholder="EMPTY" value="<%= dream.getDate()%>" class="table-input">
            </label>
        </td>
        <td>
            <label>
                <select>
                    <% int quality = dream.getQuality();%>
                    <option value=1 <%=quality == 1 ? "selected" : "" %>>1/10</option>
                    <option value=2 <%=quality == 2 ? "selected" : "" %>>2/10</option>
                    <option value=3 <%=quality == 3 ? "selected" : "" %>>3/10</option>
                    <option value=4 <%=quality == 4 ? "selected" : "" %>>4/10</option>
                    <option value=5 <%=quality == 5 ? "selected" : "" %>>5/10</option>
                    <option value=6 <%=quality == 6 ? "selected" : "" %>>6/10</option>
                    <option value=7 <%=quality == 7 ? "selected" : "" %>>7/10</option>
                    <option value=8 <%=quality == 8 ? "selected" : "" %>>8/10</option>
                    <option value=9 <%=quality == 9 ? "selected" : "" %>>9/10</option>
                    <option value=10 <%=quality == 10 ? "selected" : "" %>>10/10</option>
                </select>
            </label>
        </td>
        <td>
            <label>
                <% boolean dreamState = dream.getDream();%>
                <select>
                    <option value=true <%=dreamState ? "selected" : "" %>>true</option>
                    <option value=false <%=!dreamState ? "selected" : "" %>>false</option>
                </select>
            </label>
        </td>
        <td>
            <label>
                <input type="text" placeholder="EMPTY" value="<%= dream.getDescription()%>" class="table-input">
            </label>
        </td>
            <%}%>
    </tbody>
</table>
<button id="addButton" style="background: none; color: white; padding: 10px;">Add entry</button>
</body>
<script src="js/getEntries.js" type="text/javascript"></script>
<script src="js/addTableLine.js" type="text/javascript"></script>
<footer></footer>
</html>
