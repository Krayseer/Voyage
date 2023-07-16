package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.requests.TripRequest;
import ru.krayseer.voyage.domain.dto.responses.FollowerResponse;
import ru.krayseer.voyage.domain.dto.responses.TripResponse;

import java.util.List;

public interface TripService {

    /**
     * Загрузить все поездки
     * @return список всех поездок
     */
    List<TripResponse> loadAllTrips();

    /**
     * Создать поездку авторизованного пользователя
     * @param username username пользователя, который создаёт поездку
     * @param tripRequest данные о создаваемой поездке
     * @return данные о сохраненной в БД поездке
     */
    TripResponse createNewTrip(String username, TripRequest tripRequest);

    /**
     * Подписаться на поездку авторизованному пользователю
     * @param tripId id поездки, на которую хочет подписаться пользователь
     * @param username username пользователя, который подписывается на поездку
     * @return данные о сохраненном в поездке подписчике
     */
    FollowerResponse subscribeFollowerOnTrip(Long tripId, String username);

    /**
     * Обновить поездку по id
     * @param tripId id изменяемой поездки
     * @param tripRequest измененные данные о поездке
     * @return данные об обновленной в БД поездке
     */
    TripResponse updateTrip(Long tripId, TripRequest tripRequest);

    /**
     * Удалить поездку
     * @param tripId id поездки
     */
    void deleteTrip(Long tripId);

}
