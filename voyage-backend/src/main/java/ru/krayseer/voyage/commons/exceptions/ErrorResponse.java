package ru.krayseer.voyage.commons.exceptions;

import ru.krayseer.voyage.commons.constants.ErrorCode;

public record ErrorResponse(String message, ErrorCode errorCode) {}
