package ru.krayseer.photostorage.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.photostorage.services.PhotoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String uuid) {
        return photoService.getPhotoByUuid(uuid);
    }

    @PostMapping
    public String uploadPhoto(@RequestParam("photo") MultipartFile file) {
        return photoService.savePhoto(file);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePhoto(@PathVariable String uuid) {
        photoService.deletePhoto(uuid);
    }

}
