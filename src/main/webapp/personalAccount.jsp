<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
    <form method = "get">
        <h1>Personal Account</h1>
        <p> <%= request.getAttribute("role")%></p>
        <p>Name: <%= request.getAttribute("lastName")%> <%= request.getAttribute("firstName")%> <%= request.getAttribute("middleName")%></p>
        <p>Address: <%= request.getAttribute("email")%></p>
        <p>phoneNumber: <%= request.getAttribute("phoneNumber")%></p>
        <p>Wallet_Score: <%= request.getAttribute("money")%></p>
        <p>Discount: <%= request.getAttribute("discount")%> % </p>

    </form>
    <form method = "get" action = "/flowershop/catalog.jsp">
        <button type = "submit"> Catalog
        <% session.removeAttribute ("user"); %>
         </button>
    </form>
</body>
</html>