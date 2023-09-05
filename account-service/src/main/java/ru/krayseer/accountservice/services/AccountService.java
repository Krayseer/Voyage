package ru.krayseer.accountservice.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.dto.Response;

public interface AccountService {

    /**
     * Загрузить данные о пользователе
     * @param username username  пользователя
     * @return данные об аккаунте
     */
    Response loadAccount(String username);

    Response loadAccount(HttpServletRequest request);

    /**
     * Получить аватарку профиля из сервиса фотографий
     * @param username авторизованный пользователь
     * @return фотография
     */
    ResponseEntity<byte[]> getAccountAvatar(String username);

    ResponseEntity<byte[]> getAccountAvatar(HttpServletRequest request);

    /**
     * Загрузить аватарку профиля
     * @param multipartFile аватарка профиля
     * @param username авторизованный пользователь
     * @return ссылка на аватарку, которую можно использовать в сервисе фотографий
     */
    Response uploadAccountPhoto(MultipartFile multipartFile, String username);

    Response uploadAccountPhoto(MultipartFile multipartFile, HttpServletRequest request);

}
