<%@ page session="true" %>

<%
    // Удаление атрибутов сессии
    session.invalidate(); // Завершаем сессию, удаляя все атрибуты

    // Перенаправление пользователя на страницу входа
    response.sendRedirect("login.jsp");
%>