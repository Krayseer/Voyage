package ru.krayseer.voyage.services.factories;

import ru.krayseer.voyage.domain.dto.Request;
import ru.krayseer.voyage.domain.dto.Response;

public abstract class BaseFactory<T, R extends Request> {

    /**
     * @param object объект, из которого нужно создать response для пользователя
     * @return созданный response из объекта
     */
    public abstract Response createResponse(T object);

    /**
     * @param request запрос, из которого нужно создать объект для БД
     * @return объект БД
     */
    public abstract T createObjectFrom(R request);

}
