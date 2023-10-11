package ru.krayseer.voyageapi.commons.constants.mapper;

import ru.krayseer.voyageapi.domain.Request;
import ru.krayseer.voyageapi.domain.Response;

/**
 * Интерфейс для создания Entity из request, и response из Entity
 * @param <T> Класс Entity
 */
public interface Mapper<T> {

    /**
     * @param object объект, из которого нужно создать response для пользователя
     * @return созданный response из объекта
     */
    Response createResponse(T object);

    /**
     * @param request запрос, из которого нужно создать объект для БД
     * @return объект БД
     */
    T createEntity(Request request);

}