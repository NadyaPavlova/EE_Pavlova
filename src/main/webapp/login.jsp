
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <meta charset="UTF-8">
        <title>Цветочный магазин</title>
    </head>
    <body>
        <form method = "post" action = "user/login">
            <table>
                <tr><td>Логин:</td><td><input type = "text" name = "login"></input></td></td>
                <tr><td>Пароль:</td><td><input type = "password" name = "password"></input></td></td>
            </table>
            <button type = "submit">Войти</button>
        </form>
        <form method = "link" action = "/flowershop/registration.jsp">
            <button type = "submit">Зарегистрироваться</button>
        </form>
    </body>
