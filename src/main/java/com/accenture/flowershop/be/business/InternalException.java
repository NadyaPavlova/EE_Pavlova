package com.accenture.flowershop.be.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalException extends Exception{

    private static final Logger LOG = LoggerFactory.getLogger(InternalException.class);

    public static final String ERROR_DAO_USER_FIND = "Ошибка при поиске данных пользователя";

     public static final String ERROR_DAO_FLOWERS_PAY = "Ошибка при оплате цветов. Данного количества нет на складе";


    public static final String ERROR_DAO_ORDER = "Ошибка при записи данных пользователя";
    public static final String ERROR_DAO_ORDER_FIND = "Ошибка при поиске данных пользователя";
    public static final String ERROR_DAO_ORDER_UPDATE = "Ошибка при обновлении данных пользователя";
    public static final String ERROR_ORDER_PAY = "Ошибка при оплате заказа. Повторите попытку позже";

    public static final String ERROR_ADD_BASKET = "Ошибка при добавление товара в корзину";
    public static final String ERROR_SESSION_NULL="Для данного действия войдите в систему";
    public static final String ERROR_ACCESS="Ошибка доступа";

    public InternalException(String message, Throwable cause){
        super(message, cause);
    }

    public InternalException(String message){
        super(message);
        LOG.error(message);
    }
}
