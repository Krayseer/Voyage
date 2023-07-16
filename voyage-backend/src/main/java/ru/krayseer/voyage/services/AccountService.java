package ru.krayseer.voyage.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.PhotoUploadResponse;

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
    ResponseEntity<byte[]> getAccountAvatar(String username);

    /**
     * Загрузить аватарку профиля
     * @param multipartFile аватарка профиля
     * @param username username авторизованного пользователя
     * @return ссылка на аватарку, которую можно использовать в сервисе фотографий
     */
    PhotoUploadResponse uploadAccountPhoto(MultipartFile multipartFile, String username);

}
