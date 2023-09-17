package ru.krayseer.accountservice.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.voyageapi.services.RemoteService;

public interface RemotePhotoService extends RemoteService {

    /**
     * Получить из сервиса фотографий аватарку профиля
     * @param account аккаунт, аватарку которого нужно загрузить
     * @return изображение (аватарка)
     */
    ResponseEntity<byte[]> getAccountPhotoFromStorage(Account account);

    /**
     * Загрузить в сервис фотографий аватарку профиля пользователя
     * @param account аккаунт, которому нужно загрузить фото профиля
     * @param multipartFile изображение, которое нужно загрузить
     * @return url загруженного изображения
     */
    String uploadPhotoInStorage(Account account, MultipartFile multipartFile);

}
