package ru.krayseer.accountservice.domain.dto.responses;

import lombok.Builder;
import lombok.Data;
import ru.krayseer.accountservice.domain.dto.Response;

@Data
@Builder
public class PhotoUploadResponse implements Response {

    private String photoUrl;

}
