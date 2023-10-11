package ru.krayseer.accountservice.domain.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.voyageapi.domain.Response;

@Data
@Builder
public class PhotoUploadResponse implements Response {

    /**
     * Url картинки
     */
    private String photoUrl;

}
