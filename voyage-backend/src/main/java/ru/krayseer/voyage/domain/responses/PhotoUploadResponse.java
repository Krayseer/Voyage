package ru.krayseer.voyage.domain.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.voyageapi.domain.Response;

@Data
@Builder
public class PhotoUploadResponse implements Response {

    private String photoUrl;

}
