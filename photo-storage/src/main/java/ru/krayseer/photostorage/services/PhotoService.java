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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.photostorage.ApplicationConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final ApplicationConfig applicationConfig;

    private final RabbitProducer rabbit;

    /**
     * Получить фотографию по uuid
     * @param uuid идентификатор фотографии
     * @return фотография
     */
    @SneakyThrows
    public ResponseEntity<Resource> getPhotoByUuid(@PathVariable String uuid) {
        Path filePath = Paths.get(applicationConfig.getStoragePath()).resolve(uuid + ".jpg");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(new UrlResource(filePath.toUri()));
    }

    /**
     * Сохранить фотографию в storage
     * @param file фотография которую нужно сохранить
     * @return uuid сохраненной фотографии
     */
    public String savePhoto(MultipartFile file) {
        if(file.isEmpty()) {
            throw new RuntimeException("Выберите файл");
        }
        String uuid = UUID.randomUUID().toString();
        try {
            File savePath = new File(applicationConfig.getStoragePath() + uuid + ".jpg");
            file.transferTo(savePath);
        } catch (IOException ex) {
            log.error("Ошибка загрузки файла: " + ex);
        }
        rabbit.sendMessage("In data storage successfully save photo with uuid " + uuid);
        return uuid;
    }

    public void deletePhoto(String uuid) {
        Path filePath = Paths.get(applicationConfig.getStoragePath()).resolve(uuid + ".jpg");
        File fileToDelete = filePath.toFile();
        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                log.info("Файл с UUID " + uuid + " успешно удален.");
            } else {
                log.error("Ошибка при удалении файла с UUID " + uuid);
            }
        } else {
            log.warn("Файл с UUID " + uuid + " не существует.");
        }
    }
}
