package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.ApplicationConfig;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.accountservice.services.RemotePhotoService;

@Service
@RequiredArgsConstructor
public class RemotePhotoServiceImpl implements RemotePhotoService {

    private static final String PHOTO_REQUEST_PARAM_NAME = "photo";

    private final ApplicationConfig applicationConfig;

    private final RestTemplate restTemplate;

    public byte[] getAccountPhotoFromStorage(Account account) {
        String url = applicationConfig.getPhotoServiceUrl() + '/' + account.getAvatarUrl();
        return restTemplate.getForEntity(url, byte[].class).getBody();
    }

    @Override
    @SneakyThrows
    public String uploadPhotoInStorage(Account account, MultipartFile multipartFile) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        var body = new LinkedMultiValueMap<>();
        body.add(PHOTO_REQUEST_PARAM_NAME, new ByteArrayResource(multipartFile.getBytes()) {
            @Override
            public String getFilename() {
                return "photo.jpg";
            }
        });

        var requestEntity = new HttpEntity<>(body, headers);
        var photoUrl = restTemplate.postForEntity(applicationConfig.getPhotoServiceUrl(), requestEntity, String.class).getBody();
        restTemplate.delete(applicationConfig.getPhotoServiceUrl() + "/" + account.getAvatarUrl());
        return photoUrl;
    }

}
