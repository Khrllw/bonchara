<%@ page import="data.User" %><%--
  Created by IntelliJ IDEA.
  User: khrll
  Date: 15.12.2024
  Time: 4:28
  To change this template use File | Settings | File Templates.
--%>
<!-- Включение сессий для отслеживания состояния пользователя -->
<%
    // Проверяем, существует ли атрибут "user" в сессии
    User currentUser = null;
    if (session.getAttribute("user") == null) {
        System.out.println("NULL SESSION");
        response.sendRedirect("http://localhost:8080/monitoring_war_exploded/login.jsp");
    } else {
        currentUser = (User) session.getAttribute("user");
    }
    assert currentUser != null;%>
<html>
<head>
    <title>Profile</title>

    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/body.css">
    <link rel="stylesheet" href="css/profile.css">
    <script async defer src="js/navbar.js" type="text/javascript"></script>
    <script async defer src="js/manage.js" type="text/javascript"></script>
</head>
<body>
<div id="nav-bar">
    <% if (currentUser.getAccessRights() == 0) {
        out.println("<button id=\"manage-btn\" onclick=\"openManagePage();\">Manage</button>");
    } else {
        out.println("<button id=\"profile-btn\" onclick=\"openProfilePage();\">Profile</button>");
    }%>
    <button id="monitoring-btn" onclick="openGeneralPage();"><h2>MONITORING</h2></button>
    <form action="logout.jsp" id="exit-btn">
        <button type="submit">Exit</button>
    </form>
</div>
<div style="height: 10vh;"></div>
<div class="content">
    <h2>User Info</h2>
    <div class="form-wrap">
        <form>
            <div class="user-data-grid">
                <label for="access-rights">Access Rights</label>
                <input type="text" id="access-rights" name="access-rights" disabled style="border-bottom-color: transparent; background-color: transparent;" value=<%=currentUser.getAccessRights() == 0? 0: 1%>>
                <label for="username">Username</label>
                <input type="text" id="username" name="username" disabled style="border-bottom-color: transparent; background-color: transparent;" value=<%=currentUser.getUserName()%>
                        required>
                <label for="lastname">LastName</label>
                <input type="text" id="lastname" name="lastname" placeholder=<%=currentUser.getLastName()%>
                       required>
                <label for="firstname">FirstName</label>
                <input type="text" id="firstname" name="firstname" placeholder=<%=currentUser.getFirstName()%>
                       required>
                <label for="patronymic">Patronymic</label>
                <input type="text" id="patronymic" name="patronymic" placeholder=<%=currentUser.getPatronymic()%>
                       required>
                <label for="password">Password</label><input type="password" id="password" name="password"
                                                             placeholder="Password"
                                                             required><br><br>

            </div>
            <button type="button" id="update-user-info"
                    onclick="updateUserInfo()">Save
            </button>
        </form>
    </div>
</div>
</body>
</html>
