package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.accountservice.services.RemotePhotoService;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

@Service
@RequiredArgsConstructor
public class RemotePhotoServiceImpl implements RemotePhotoService {

    private static final String PHOTO_REQUEST_PARAM_NAME = "photo";

    private final RestTemplate restTemplate;

    public ResponseEntity<byte[]> getAccountPhotoFromStorage(Account account) {
        String url = String.format("%s/%s", baseUrl(), account.getAvatarUrl());
        return restTemplate.getForEntity(url, byte[].class);
    }

    @Override
    @SneakyThrows
    public String uploadPhotoInStorage(Account account, MultipartFile multipartFile) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, ByteArrayResource> body = new LinkedMultiValueMap<>();
        body.add(PHOTO_REQUEST_PARAM_NAME, new ByteArrayResource(multipartFile.getBytes()) {
            @Override
            public String getFilename() {
                return "photo.jpg";
            }
        });

        HttpEntity<LinkedMultiValueMap<String, ByteArrayResource>> entity = new HttpEntity<>(body, headers);
        String photoUrl = restTemplate.postForEntity(baseUrl(), entity, String.class).getBody();
        restTemplate.delete(String.format("%s/%s", baseUrl(), account.getAvatarUrl()));
        return photoUrl;
    }

    public String baseUrl() {
        return "http://localhost:8765/photo-storage/photo";
    }

}
