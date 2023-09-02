package ru.krayseer.accountservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.dto.responses.AccountResponse;
import ru.krayseer.accountservice.domain.dto.responses.PhotoUploadResponse;

public interface AccountService {

    /**
     * Загрузить данные об авторизованном пользователе
     * @param username username авторизованного пользователя
     * @return данные об аккаунте
     */
    AccountResponse loadAccount(String username);

    /**
     * Получить аватарку профиля из сервиса фотографий
     * @param username username авторизованного пользователя
     * @return фотография
     */
    byte[] getAccountAvatar(String username);

    /**
     * Загрузить аватарку профиля
     * @param multipartFile аватарка профиля
     * @param username username авторизованного пользователя
     * @return ссылка на аватарку, которую можно использовать в сервисе фотографий
     */
    PhotoUploadResponse uploadAccountPhoto(MultipartFile multipartFile, String username);

}
