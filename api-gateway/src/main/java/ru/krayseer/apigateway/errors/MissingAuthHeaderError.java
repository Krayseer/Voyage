package ru.krayseer.apigateway.errors;

public class MissingAuthHeaderError extends BaseError {

    public MissingAuthHeaderError() {
        super("Отсутствует заголовок авторизации", ErrorCode.MISSING_AUTHORIZATION_HEADER);
    }

}
