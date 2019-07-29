
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>

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
            <tr><td> Логин:</td><td> <input type="text" name="email" value="" id="email">
                <div id="results"></div>
                   <script>
                        email.onblur = function() {
                            if(email.value != ""){
                                $.ajax({
                                    url: "http://localhost:8080/flowershop/ws/rest/login/user/"+this.value,
                                    type: "GET",
                                    success: function(data) {
                                        if(data == "false"){
                                            email.className = "success";
                                            $('#results').html('<font color="green">Логин свободен</font>');
                                            button.disabled = "";
                                        }
                                        else{
                                            email.className = "error";
                                            $('#results').html('<font color="red">Такой логин уже занят</font>');
                                            button.disabled = "disabled";
                                        }
                                    },
                                    error: function(data){
                                        $('#results').html('<p>Возникла неизвестная ошибка. Не получен ответ с сервера</p>');
                                    }
                                });
                            }
                        };
                        email.onfocus = function(){
                            email.className = "";
                            $('#results').html('');
                        };
                   </script>
                </input>
            <td></tr>
            <tr><td>Номер телефона:</td><td><input type = "phoneNumber" name = "phoneNumber"></input></td></td>
            <tr><td>Пароль:</td><td><input type = "password" name = "password"></input></td></td>
            </table>
        <br>
        <button type = "submit">Зарегистрироваться</button>
    </form>
    </div>

</body>
