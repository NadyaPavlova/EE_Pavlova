<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
<h2>LOGIN PAGE</h2>
<form method = "post" action = "user/login">
    <input type = "text" name = "login"></input>
    <input type = "password" name = "password"></input>
    <button type = "submit">Login</button>
</form>
<form method = "link" action = "/flowershop/registration.jsp">
    <button type = "submit">Registration</button>
</form>
</body>
</html>