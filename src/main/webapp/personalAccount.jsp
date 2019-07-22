
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<head>
    <meta charset="UTF-8">
    <title>Цветочнный магазин</title>
</head>
<body>
    <form method = "get">
        <h1 style="color: red">Личный кабинет</h1>
        <div>
            <p> ${role} </p>
            <p>Ф.И.О: ${user.lastName} ${user.firstName} ${user.middleName}</p>
            <p>Электронный адрес: ${user.email} </p>
            <p>Номер телефона: ${user.phoneNumber} </p>
            <p>Счет: ${user.money} руб.</p>
            <p>Скидка: ${user.discount}%</p>
            </div>
    </form>
   <h2 style="color: rgb(393, 9, 100)">Каталог</h2>
           <table style="align:center; width:50%; vertical-align:middle">
               <tr style="background-color:rgb(342, 94, 125)">
                   <td>Id</td>
                   <td>Название</td>
                   <td>Цена</td>
                   <td>Кол-во на складе</td>
               </tr>
               <c:forEach items = "${flowers}" var="iterator">
                   <tr style="background-color:rgb(342, 169, 169)">
                       <td>${iterator.idFlower}</td>
                       <td>${iterator.nameFlower}</td>
                       <td>${iterator.price} руб.</td>
                       <td>${iterator.qtyStock}</td>
                       <form method="post"  action = "/flowershop/user/BasketAddServlet">
                           <td style="align:center; width:10%;"><input type="text" name="quantity" placeholder="quantity"></td>
                           <td style="background-color: rgb(255, 255, 255)"><button type="submit" name="idFlower" value="${iterator.idFlower}">В корзину</button></td>
                       </form>
                   </tr>
               </c:forEach>
           </table>


<h2 style="color: rgb(139, 20, 155)">Корзина</h2>
        <form method="post" action="/flowershop/user/BasketDeleteServlet">
            <table style="width:50%;" >
                <tr style="background-color:rgb(162, 77, 147)">
                    <td>Id</td>
                    <td>Название</td>
                    <td>Кол-во</td>
                    <td>Цена</td>
                </tr>
                <c:forEach items = "${basket.basketList}" var="iterator">
                    <tr style="background-color: rgb(204, 140, 203)">
                        <td>${iterator.idFlower}</td>
                        <td>${iterator.nameFlower}</td>
                        <td>${iterator.qtyFlower}</td>
                        <td>${iterator.priceFlower} руб.</td>
                        <td style="background-color: rgb(255, 255, 255)">
                            <button type="submit" name="idFlower" value="${iterator.idFlower}"> Удалить </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
             <p>Сумма заказа: ${basket.priceSum} руб.</p>
             <p style="color: red"> ${count} </p>
        </form>
</body>
</html>