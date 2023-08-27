package ru.krayseer.photostorage.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoService {

    private static final String STORAGE_PATH = "C:\\Home\\Java\\voyage\\photo-storage\\src\\main\\resources\\storage\\";

    private final RabbitProducer rabbit;

    @SneakyThrows
    public ResponseEntity<Resource> getPhotoByUuid(@PathVariable String uuid) {
        Path filePath = Paths.get(STORAGE_PATH).resolve(uuid + ".jpg");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(new UrlResource(filePath.toUri()));
    }

    public String savePhoto(MultipartFile file) {
        if(file.isEmpty()) {
            throw new RuntimeException("Выберите файл");
        }
        String uuid = UUID.randomUUID().toString();
        try {
            File savePath = new File(STORAGE_PATH + uuid + ".jpg");
            file.transferTo(savePath);
        } catch (IOException ex) {
            log.error("Ошибка загрузки файла: " + ex);
        }
        rabbit.sendMessage("In data storage successfully save photo with uuid " + uuid);
        return uuid;
    }

}
