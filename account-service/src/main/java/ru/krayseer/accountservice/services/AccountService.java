package ru.krayseer.accountservice.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.voyageapi.domain.dto.Response;

public interface AccountService {

    /**
     * Загрузить данные о пользователе
     * @param username пользователь
     * @return данные об аккаунте
     */
    Response loadAccount(String username);

    /**
     * Загрузить данные об авторизованном пользователе
     * @param request запрос с данными об аккаунте, о котором хотят получить информацию
     * @return данные об авторизованном пользователе
     */
    Response loadAccount(HttpServletRequest request);

    /**
     * Получить аватарку профиля из сервиса фотографий
     * @param username пользователь
     * @return фотография
     */
    ResponseEntity<byte[]> getAccountAvatar(String username);

    /**
     * Получить аватарку профиля авторизованного пользователя из сервиса фотографий
     * @param request запрос с данными об аккаунте, о котором хотят получить информацию
     * @return фотографий авторизованного пользователя
     */
    ResponseEntity<byte[]> getAccountAvatar(HttpServletRequest request);

    /**
     * Загрузить аватарку профиля
     * @param multipartFile аватарка профиля
     * @param username авторизованный пользователь
     * @return ссылка на аватарку, которую можно использовать в сервисе фотографий
     */
    Response uploadAccountPhoto(MultipartFile multipartFile, String username);

    /**
     * Загрузить аватарку профиля авторизованного пользователя
     * @param multipartFile аватарка профиля
     * @param request запрос с данными об аккаунте, о котором хотят получить информацию
     * @return ссылка на аватарку, которую можно использовать в сервисе фотографий
     */
    Response uploadAccountPhoto(MultipartFile multipartFile, HttpServletRequest request);

}
