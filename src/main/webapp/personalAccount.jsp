
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="UTF-8">
    <title>Цветочный магазин</title>
</head>
<body>
    <div style="width: 600px; position: absolute; left: 27%; margin: 0 0 0 0;">
        <h1 style="color: red">Личный кабинет</h1>
        <div>
            <p> ${role} </p>
            <p>Ф.И.О: ${user.lastName} ${user.firstName} ${user.middleName}</p>
            <p>Электронный адрес: ${user.email} </p>
            <p>Номер телефона: ${user.phoneNumber} </p>
            <p>Счет: ${user.money} руб.</p>
            <p>Скидка: ${user.discount}%</p>
        </div>

   <h2 style="color: rgb(393, 9, 100)">Каталог</h2>
   <form method="post"  action = "/flowershop/user/FilterServer">
    <table style="width:100%;">
      <tr>
          <td><input type="text" name="nameFlower" placeholder="nameFlower"></input></td>
          <td><input type="text" name="minPrice" placeholder="minPrice"></input></td>
          <td><input type="text" name="maxPrice" placeholder="maxPrice"></input></td>
          <td><button type="submit"> Поиск </button></td>
      </tr>
      <br>
   </form>
           <table style="text-align:center;  width:100%;">
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
                           <td style="align:center; width:10%;"><input type="text" name="quantity" placeholder="quantity"></input></td>
                           <td style="background-color: rgb(255, 255, 255)"><button type="submit" name="idFlower" value="${iterator.idFlower}">В корзину</button></td>
                       </form>
                   </tr>
               </c:forEach>
           </table>

        <c:if test = "${role eq 'User'}">
        <h2 style="color: rgb(139, 20, 155)">Корзина</h2>
        <form method="post" action="/flowershop/user/BasketDeleteServlet">
            <table style=" text-align:center; width:100%;" >
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
                 <fmt:formatNumber value="${basket.priceSum}" pattern="0.00" var="totalAmountApplied" />
                     <c:if test = "${basket.priceSum eq '0.00'}">
                        <button type="submit"> Оформить заказ </button>
                     </c:if>
                 </c:if>
            </c:if>
        </form>
        </c:if>
    <h2 style="color: rgb(71, 160, 240)">Заказы</h2>
    <c:forEach items = "${orders}" var="i">


    <table style="text-align:center; width:100%;">
        <tr style="background-color:rgb(26, 180, 253)">
            <td> Заказ №${i.idOrder}</td>
            <td> Статус: ${i.status} </td>
            <td> Создан:<calendar:formatDate  value="${i.creationDate}" pattern="dd-MM-yyyy"/></td>
            <td>
                <c:if test = "${i.status eq 'closed'}">
                     Закрыт:<calendar:formatDate  value="${i.closingDate}" pattern="dd-MM-yyyy"/>
                </c:if>
            </td>
            <c:if test = "${role eq 'User'}">
                <c:if test = "${i.status eq 'generated'}">
                    <form method="post"  action = "/flowershop/user/OrderPayService">
                        <td style="background-color:rgb(255, 255, 255)"><button type="submit" name="idOrder" value="${i.idOrder}"> Оплатить заказ </button></td>
                    </form>
                </c:if>
            </c:if>
            <c:if test = "${role eq 'Admin'}">
                <c:if test = "${i.status eq 'paid'}">
                    <form method="post"  action = "/flowershop/user/OrderClosedService">
                        <td style="background-color:rgb(255, 255, 255)"><button type="submit" name="idOrder" value="${i.idOrder}"> Закрыть заказ </button></td>
                    </form>
                </c:if>
            </c:if>
        </tr>
    </table>

         <table style="text-align:center; width:100%;" >
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
             <tr>
                <td> </td>
                <td> </td>
                <td style="background-color:rgb(165, 212, 255)">Сумма заказа:</td>
                <td style="background-color:rgb(165, 212, 255)">${i.priceSum} руб.</td>
             </tr>
         </table>
         <br><hr><br>
    </c:forEach>

   <form method="post"  action = "/flowershop/personalAccountServlet">
        <button type="submit"> Обновить данные </button>
   </form>
   </div>

</body>
</html>