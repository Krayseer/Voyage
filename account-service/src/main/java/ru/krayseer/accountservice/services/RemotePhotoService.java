package ru.krayseer.accountservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.entities.Account;

public interface RemotePhotoService {

    byte[] getAccountPhotoFromStorage(Account account);

    String uploadPhotoInStorage(Account account, MultipartFile multipartFile);

}
