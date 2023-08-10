package ru.krayseer.voyage.domain.mappers;

import ru.krayseer.voyage.domain.dto.Request;
import ru.krayseer.voyage.domain.dto.Response;

/**
 * Интерфейс для создания Entity из request, и response из Entity
 * @param <T> Класс Entity
 * @param <R> Реализация Request для конкретного Entity
 */
public interface Mapper<T, R extends Request> {

    /**
     * @param object объект, из которого нужно создать response для пользователя
     * @return созданный response из объекта
     */
    Response createResponse(T object);

    /**
     * @param request запрос, из которого нужно создать объект для БД
     * @return объект БД
     */
    T createEntity(R request);

}
