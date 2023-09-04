package ru.krayseer.accountservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.dto.Response;

public interface AccountService {

    /**
     * Загрузить данные о пользователе
     * @param username username  пользователя
     * @return данные об аккаунте
     */
    Response loadAccount(String username);

    /**
     * Получить аватарку профиля из сервиса фотографий
     * @param username авторизованный пользователь
     * @return фотография
     */
    byte[] getAccountAvatar(String username);

    /**
     * Загрузить аватарку профиля
     * @param multipartFile аватарка профиля
     * @param username авторизованный пользователь
     * @return ссылка на аватарку, которую можно использовать в сервисе фотографий
     */
    Response uploadAccountPhoto(MultipartFile multipartFile, String username);

}
