
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">

    <title>Цветочный магазин</title>
</head>
<body>
     <div style="width: 250px;  height: 250px; position: absolute; top: 50%; left: 50%; margin: -125px 0 0 -125px;">

        <form method = "link" action = "/flowershop/login.jsp">
            <button type = "submit">Войти</button>
        </form>
        <form method = "link" action = "/flowershop/registration.jsp">
            <button type = "submit">Зарегистрироваться</button>
        </form>
     </div>

</body>
</html>