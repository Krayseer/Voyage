package ru.krayseer.voyage.services;

import ru.krayseer.voyageapi.domain.dto.Response;
import ru.krayseer.voyage.domain.dto.requests.CarRequest;
import ru.krayseer.voyage.domain.dto.responses.CarResponse;

import java.util.List;

public interface CarService {

    /**
     * @param id идентификатор автомобиля
     * @return данные об автомобиле
     */
    Response loadCar(Long id);

    /**
     * Загрузить автомобили авторизованного пользователя
     *
     * @param token username пользователя
     * @return список автомобилей
     */
    List<CarResponse> loadUserCars(String username);

    /**
     * Добавить автомобиль авторизованному пользователю
     * @param token username пользователя
     * @param carRequest данные о автомобиле
     * @return данные о сохраненном в БД автомобиле
     */
    Response addUserCar(String username, CarRequest carRequest);

    /**
     * Обновить данные об автомобиле по id
     * @param carId id автомобиля
     * @param carRequest обновлённые данные
     * @return данные о сохраненном в БД автомобиле
     */
    Response updateCar(Long carId, CarRequest carRequest);

    /**
     * Удалить автомобиль, если данный автомобиль не зарегистрирован в поездке
     * @param carId id удаляемого автомобиля
     */
    void deleteCar(Long carId);

}
