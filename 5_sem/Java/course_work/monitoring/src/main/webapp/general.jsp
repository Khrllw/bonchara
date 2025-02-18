<%@ page import="java.util.Map" %>
<%@ page import="static db.DatabaseManager.getCurrentDestemmerCrusherParams" %>
<%@ page import="data.User" %>
<%@ page import="static java.awt.SystemColor.window" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <!-- <meta http-equiv="refresh" content="1"> -->
    <title>Monitor</title>

    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/body.css">
    <link rel="stylesheet" href="css/general.css">
</head>

<body>
<script async defer src="js/navbar.js" type="text/javascript"></script>
<script async defer src="js/general.js" type="text/javascript"></script>
<div id="nav-bar">
    <% if (currentUser.getAccessRights() == 0) {
        out.println("<button id=\"manage-btn\" onclick=\"openManagePage();\">Manage</button>");
    } else {
        out.println("<button id=\"profile-btn\" onclick=\"openProfilePage();\">Profile</button>");
    }%>
    <button id="monitoring-btn" onclick="openGeneralPage();"><h2>MONITORING</h2></button>
    <form action="logout.jsp" id="exit-btn" style="font-size: inherit;">
        <button type="submit">Exit</button>
    </form>
</div>
<div style="height: 10vh;"></div>
<div class="content">
    <div class="header">
        <h2>Production equipment</h2>
    </div>
    <div id="eq-grid">
        <%Map<String, Integer> currentDestemmerCrusherParams = getCurrentDestemmerCrusherParams();%>
        <div class="block-wrap" onclick="showPopup(1);">
            <text>1</text>
            <h4>Destemmer-Crusher</h4>
            <p id="destemmer-crusher-parametres"></p>
        </div>
        <div class="block-wrap">
            <text>2</text>
            <h4>Wine Press</h4>
            <p id="wine-press-parametres"></p>
        </div>
        <div class="block-wrap">
            <text>3</text>
            <h4>Fermentation Tanks</h4>
            <p id="fermentation-tanks-parametres"></p>
        </div>
        <div class="block-wrap" >
            <text>4</text>
            <h4>Chilling Unit</h4>
            <p id="chilling-unit-parametres"></p>
        </div>
        <div class="block-wrap" >
            <text>5</text>
            <h4>Filtering Equipment</h4>
            <p id="filtering-equipment-parametres"></p>
        </div>
        <div class="block-wrap" >
            <text>6</text>
            <h4>Wine Pump</h4>
            <p id="wine-pump-parametres"></p>
        </div>
        <div class="block-wrap" >
            <text>7</text>
            <h4>Thermal Shock Tank</h4>
            <p id="thermal-shock-tank-parametres"></p>
        </div>
        <div class="block-wrap" >
            <text>8</text>
            <h4>Fining Equipment</h4>
            <p id="fining-equipment-parametres"></p>
        </div>
        <div class="block-wrap">
            <text>9</text>
            <h4>Bottling Line</h4>
            <p id="bottling-line-parametres">
            </p>
        </div>
        <div class="block-wrap" >
            <text>10</text>
            <h4>CO₂ Treatment Unit</h4>
            <p id="treatment-unit-parametres">
            </p>
        </div>
        <div class="block-wrap" >
            <text>11</text>
            <h4>Electromagnetic Treatment Unit</h4>
            <p id="electromagnetic-treatment-unit-parametres">

            </p>
        </div>
    </div>
</div>
<div class="content">
    <div class="header">
        <h2>Statistics</h2>
    </div>
    <div id="stat-grid">
        <div class="block-wrap" id="sg-2">
            <h4>Raw Material</h4>
            <div id="raw-data-grid">
                <text>Grape Tones</text>
                <text>10000</text>
                <text>Sugar content (°Bx)</text>
                <text>24</text>
                <text>Acidity</text>
                <text>3 pH</text>
                <text>Humidity</text>
                <text>9 gpl</text>
                <text>Relative losses</text>
                <text>30%</text>
            </div>
        </div>
        <div class="block-wrap" id="sg-1">
            <h4>Брак и потери</h4>
            <div id="defect-data-grid">
                <text>Destemmer-Crusher</text>
                <text style="margin: auto;">20%</text>
                <text>Wine Press</text>
                <text style="margin: auto;">2%</text>
                <text>Fermentation Tanks</text>
                <text style="margin: auto;">1%</text>
                <text>Chilling Unit</text>
                <text style="margin: auto;">0%</text>
                <text>Filtering Equipment:</text>
                <text style="margin: auto;">11%</text>
                <text>Wine Pump</text>
                <text style="margin: auto;">12%</text>
                <text>Thermal Shock Tank</text>
                <text style="margin: auto;">2%</text>
                <text>Fining Equipment</text>
                <text style="margin: auto;">12%</text>
                <text>Bottling Line</text>
                <text style="margin: auto;">43%</text>
                <text>Treatment Unit</text>
                <text style="margin: auto;">43%</text>
                <text>Electromagnetic Treatment Unit</text>
                <text style="margin: auto;">32%</text>
                <text style="grid-column: 1/2; grid-row: 12/14; font-weight: bolder;">Economic damage</text>
                <text style="margin: auto; grid-column: 2/3; grid-row: 12/14; font-weight: bolder;">100000 rub</text>
            </div>
        </div>

        <div class="block-wrap">
            <h4>Resourses</h4>
            <div id="resourses-data-grid">
                <text>Energy</text>
                <text style="margin: auto;">10000 kWh</text>
                <text>Water</text>
                <text style="margin: auto;">1500 M^3</text>
                <text>Steam</text>
                <text style="margin: auto;">20000 KG</text>
                <text>Gas</text>
                <text style="margin: auto;">3000 M^3</text>
            </div>
        </div>

        <div class="block-wrap">
            <h4>Finance</h4>
            <div id="finance-data-grid">
                <text>Cost</text>
                <text style="margin: auto;">70000 rub</text>
                <text>Profit</text>
                <text style="margin: auto;">120000 rub</text>
                <text>Profitability</text>
                <text style="margin: auto;">40%</text>
            </div>
        </div>
        <div class="block-wrap">
            <h4>Reports</h4>
            <div id="report-data-grid">
                <text>Shift report</text>
                <a href="files/shiftReport.txt" download>Скачать TXT</a>
                <text>Month report</text>
                <a href="files/monthReport.txt" download>Скачать TXT</a>
                <text>Year report</text>
                <a href="files/yearReport.txt" download>Скачать TXT</a>

            </div>

        </div>
    </div>

</div>
<div id="pop-up-wrap" class="hidden">
    <div id="pop-up">
        <div class="x" onclick="closePopup();"></div>
        <h2>Destemmer-crusher</h2>
        <canvas id="temperature-canv"></canvas>
        <h4 class="axis-label" style="top: 40%;">Temperature</h4>
        <h4>Time</h4>
        <canvas id="speed-canv"></canvas>
        <h4 class="axis-label" style="top: 80%;">Speed</h4>
        <h4>Time</h4>
    </div>
</div>


</body>

</html>