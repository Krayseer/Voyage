package ru.krayseer.voyage.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.voyage.domain.dto.Response;

@Data
@Builder
public class PhotoUploadResponse implements Response {

    private String photoUrl;

}
