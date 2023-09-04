package ru.krayseer.accountservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.entities.Account;

public interface RemotePhotoService {

    /**
     * Получить из сервиса фотографий аватарку профиля
     * @param account аккаунт, аватарку которого нужно загрузить
     * @return изображение (аватарка)
     */
    byte[] getAccountPhotoFromStorage(Account account);

    /**
     * Загрузить в сервис фотографий аватарку профиля пользователя
     * @param account аккаунт, которому нужно загрузить фото профиля
     * @param multipartFile изображение, которое нужно загрузить
     * @return url загруженного изображения
     */
    String uploadPhotoInStorage(Account account, MultipartFile multipartFile);

}
