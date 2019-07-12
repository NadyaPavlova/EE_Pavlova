<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
<h2>Registration</h2>
<form method = "post" action = "registration">
<table>
    <tr><td>middleName:</td><td><input type = "middleName" name = "middleName"></input></td></td>
    <tr><td>firstName:</td><td><input type = "firstName" name = "firstName"></input></td></td>
    <tr><td>lastName:</td><td><input type = "lastName" name = "lastName"></input></td></td>
    <tr><td>email:</td><td><input type = "email" name = "email"></input></td></td>
    <tr><td>phoneNumber:</td><td><input type = "phoneNumber" name = "phoneNumber"></input></td></td>
    <tr><td>password:</td><td><input type = "password" name = "password"></input></td></td>
</table>
<br>
    <button type = "submit">To register</button>
</form>

</body>
</html>