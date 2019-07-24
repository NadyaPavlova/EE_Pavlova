
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<head>
    <meta charset="UTF-8">
    <title>Цветочный магазин</title>
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
           <table style="text-align:center; width:50%;">
               <tr style="background-color:rgb(255, 94, 125)">
                   <td>Id</td>
                   <td>Название</td>
                   <td>Цена</td>
                   <td>Кол-во на складе</td>
                   <td>Кол-во для покупки</td>
               </tr>
               <c:forEach items = "${flowers}" var="iterator">
                   <tr style="background-color:rgb(255, 169, 169)">
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
            <table style=" text-align:center; width:50%;" >
                <tr style="background-color:rgb(162, 77, 147)">
                    <td>Id</td>
                    <td>Название</td>
                    <td>Кол-во</td>
                    <td>Цена</td>
                </tr>
                <c:forEach items = "${basket.basketList}" var="iterator">
                    <tr style="background-color: rgb(204, 140, 203)">
                        <td>${iterator.flowerDTO.idFlower}</td>
                        <td>${iterator.flowerDTO.nameFlower}</td>
                        <td>${iterator.qtyFlower}</td>
                        <td>${iterator.priceFlower} руб.</td>
                        <td style="background-color: rgb(255, 255, 255)">
                            <button type="submit" name="idFlower" value="${iterator.flowerDTO.idFlower}"> Удалить </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <form method="post"  action = "/flowershop/user/OrderService">
            <p>Сумма заказа: ${basket.priceSum} руб.</p>
            <p style="color: red"> ${errCount} </p>
            <p style="color: red"> ${errPrice} </p>
            <c:if test = "${errCount eq ''}">
                <c:if test = "${errPrice eq ''}">
                    <button type="submit"> Оформить заказ </button>
                 </c:if>
            </c:if>
        </form>

    <h2 style="color: rgb(71, 160, 240)">Заказы</h2>
    <c:forEach items = "${orders}" var="i">
        <p>Заказ №${i.idOrder}</p>
        <p>Статус ${i.status}
        <c:if test = "${i.status eq 'generated'}">
            <form method="post"  action = "/flowershop/user/OrderPayService">
                <button type="submit" name="idOrder" value="${i.idOrder}"> Оплатить заказ </button>
            </form>
        </c:if>

        </p>
         <table style="text-align:center; width:50%;" >
             <tr style="background-color:rgb(98, 192, 237)">
                <td>Id</td>
                <td>Название</td>
                <td>Кол-во</td>
                <td>Цена</td>
             </tr>

            <c:forEach items = "${i.basketList}" var="iterator">
                <tr style="background-color:rgb(165, 212, 255)">
                    <td>${iterator.flowerDTO.idFlower}</td>
                    <td>${iterator.flowerDTO.nameFlower}</td>
                    <td>${iterator.qtyFlower}</td>
                    <td>${iterator.priceFlower} руб.</td>
                </tr>
             </c:forEach>
         </table>
         <p> Сумма заказа: ${i.priceSum}</p>
    </c:forEach>
</body>
</html>