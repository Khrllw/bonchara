<%@ page import="data.User" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: khrll
  Date: 15.12.2024
  Time: 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Проверяем, существует ли атрибут "user" в сессии
    User currentUser = null;
    if (session.getAttribute("user") == null) {
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

<div class="content" id="control-content">
    <h2>Control Panel</h2>
    <div id="control-data-grid">
        <div class="form-wrap">
            <h3>User Info</h3>
            <form>
                <div class="user-data-grid">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder=<%=currentUser.getUserName()%>
                           required>
                    <label for="access-rights">Access Rights</label>
                    <input type="text" id="access-rights" name="access-rights" placeholder="0 [Admin] / 1 [Base User]"
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
                                                                 required>
                </div>
                <br>
                <button type="button" id="update-user-info"
                        onclick="updateUserInfo();">Save
                </button>
            </form>
        </div>
        <div class="form-wrap">
            <h3>Delete User</h3>
            <form>
                <div class="user-data-grid" style="grid-template-rows: 1fr;">
                    <label for="delete-username">Username<br>to delete</label>
                    <input type="text" id="delete-username"
                           name="delete-username"
                           placeholder="Username to delete"
                           required>
                </div>
                <br>
                <button type="button" id="delete-user"
                        onclick="deleteUser()">Delete
                </button>
            </form>
        </div>
        <!--
        <div class="form-wrap" id="eq-params-control">
            <h3>Брак и потери</h3>
            <form id="defect-data-grid">
                <label for="destemmer-crusher-params">Destemmer-Crusher</label>
                <input type="text" id="destemmer-crusher-params"
                       name="destemmer-crusher-params"
                       placeholder="destemmer-crusher-params"
                       required>
                <text>Destemmer-Crusher</text>
                <text>2%</text>
                <text>Wine Press</text>
                <text>2%</text>
                <text>Fermentation Tanks</text>
                <text>2%</text>
                <text>Chilling Unit</text>
                <text>2%</text>
                <text>Filtering Equipment:</text>
                <text>2%</text>
                <text>Wine Pump</text>
                <text>2%</text>
                <text>Thermal Shock Tank</text>
                <text>2%</text>
                <text>Fining Equipment</text>
                <text>2%</text>
                <text>Bottling Line</text>
                <text>2%</text>
                <text>Treatment Unit</text>
                <text>2%</text>
                <text>Electromagnetic Treatment Unit</text>
                <text>2%</text>
                <button type="button" id="change-eq-info"
                        onclick="changeEquipmentInfo()">Save
                </button>
            </form>

        </div>
        -->
    </div>
</div>
</body>
</html>
