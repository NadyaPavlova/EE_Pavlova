<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
    <form method = "get">
        <h1 style="color: red">Personal Account</h1>
        <div>
            <p> ${role} </p>
            <p>Name: ${user.lastName} ${user.firstName} ${user.middleName}</p>
            <p>Address: ${user.email} </p>
            <p>phoneNumber: ${user.phoneNumber} </p>
            <p>Money: ${user.money}</p>
            <p>Discount: ${user.discount}%</p>
            </div>
    </form>
   <form method="post"  action = "/flowershop/user/BasketServlet">
   <h2 style="color: rgb(393, 9, 100)">Catalog flowers</h2>
           <table>
               <tr style="background-color:rgb(342, 94, 125)">
                   <td>Id</td>
                   <td>Name</td>
                   <td>Price</td>
                   <td>Qty</td>
               </tr>
               <c:forEach items = "${flowers}" var="iterator">
                   <tr style="border:2px solid black;background-color:rgb(342, 169, 169)" class="col-md-12 col-sm-4 grid-item">
                       <td>${iterator.idFlower}</td>
                       <td>${iterator.nameFlower}</td>
                       <td>${iterator.price}</td>
                       <td>${iterator.qtyStock}</td>
                       <td><button  type="submit" name="idFlower" value="${iterator.idFlower}"  > Buy </button>
                       </td>
                   </tr>
               </c:forEach>
           </table>
       </form>

<h2 style="color: rgb(139, 20, 155)">Basket</h2>
        <form method="post" action="/flowershop/user/BasketDeleteServlet">
            <table>
                <tr style="background-color:rgb(162, 77, 147)">
                    <td>Id</td>
                    <td>Name</td>
                    <td>Quantity</td>
                </tr>
                <c:forEach items = "${basket.basketList}" var="iterator">
                    <tr  style="border:2px solid black;background-color: rgb(204, 140, 203)" class="col-md-12 col-sm-4 grid-item">
                        <td>${iterator.idFlower}</td>
                        <td>${iterator.nameFlower}</td>
                        <td>${iterator.qtyFlower}</td>
                        <td><button type="submit" name="idFlower" value="${iterator.idFlower}"> Delete </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
             <p style="color: red"> ${count} </p>
        </form>
</body>
</html>