package ru.krayseer.voyageapi.commons.constants;

public class RegExp {

    /**
     * Корректный телефонный номер
     */
    public final static String PHONE_NUMBER = "(^8|7|\\+7)((\\d{10})|(\\s\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}))";

    /**
     * Корректный номер автомобиля
     */
    public final static String CAR_NUMBER = "^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";

}
