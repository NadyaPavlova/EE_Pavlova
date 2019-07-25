
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<head>
    <meta charset="UTF-8">
    <title>Цветочный магазин</title>
</head>
<body>
     <div style="width: 250px;  height: 250px; position: absolute; top: 50%; left: 50%; margin: -125px 0 0 -125px;">
    <h2>Регистрация</h2>
    <p style="color: red">${errorRegistration}</p>
    <form method = "post" action = "registration">
        <table>
            <tr><td>Фамилия:</td><td><input type = "lastName" name = "lastName"></input></td></td>
            <tr><td>Имя:</td><td><input type = "firstName" name = "firstName"></input></td></td>
            <tr><td>Отчество:</td><td><input type = "middleName" name = "middleName"></input></td></td>
            <p style="color: red">${errorLogin}</p>
            <tr><td>Электронная почта:</td><td><input type = "email" name = "email"></input></td></td>
            <tr><td>Номер телефона:</td><td><input type = "phoneNumber" name = "phoneNumber"></input></td></td>
            <tr><td>Пароль:</td><td><input type = "password" name = "password"></input></td></td>
        </table>
        <br>
        <button type = "submit">Зарегистрироваться</button>
    </form>
    </div>

</body>
