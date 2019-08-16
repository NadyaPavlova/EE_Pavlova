package com.accenture.flowershop.be.business;

public class InternalException extends Exception{

    public static final String ERROR_DAO_USER_FIND = "Ошибка при поиске данных пользователя";

     public static final String ERROR_DAO_FLOWERS_PAY = "Ошибка при оплате цветов. Данного количества нет на складе";


    public static final String ERROR_DAO_ORDER = "Ошибка при записи данных пользователя";
    public static final String ERROR_DAO_ORDER_FIND = "Ошибка при поиске данных пользователя";
    public static final String ERROR_DAO_ORDER_UPDATE = "Ошибка при обновлении данных пользователя";
    public static final String ERROR_ORDER_PAY = "Ошибка при оплате заказа. Повторите попытку позже";

    public static final String ERROR_ADD_BASKET = "Ошибка при добавление товара в корзину";


    public InternalException(String message, Throwable cause){
        super(message, cause);
    }
}
