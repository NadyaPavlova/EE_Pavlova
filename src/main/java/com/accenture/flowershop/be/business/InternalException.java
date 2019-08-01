package com.accenture.flowershop.be.business;

public class InternalException extends Exception{

    public static final String ERROR_DAO_USER = "Ошибка при записи данных пользователя";
    public static final String ERROR_DAO_USER_FIND = "Ошибка при поиске данных пользователя";
    public static final String ERROR_DAO_USER_UPDATE = "Ошибка при обновлении данных пользователя";

    public static final String ERROR_DAO_FLOWERS_FIND = "Ошибка при поиске цветов";
    public static final String ERROR_DAO_FLOWERS_UPDATE = "Ошибка при обновлении цветов";


    public static final String ERROR_DAO_ORDER = "Ошибка при записи данных пользователя";
    public static final String ERROR_DAO_ORDER_FIND = "Ошибка при поиске данных пользователя";
    public static final String ERROR_DAO_ORDER_UPDATE = "Ошибка при обновлении данных пользователя";


    public InternalException(String message, Throwable cause){
        super(message, cause);
    }
}
