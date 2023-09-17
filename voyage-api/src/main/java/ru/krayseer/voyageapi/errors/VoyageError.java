package ru.krayseer.voyageapi.errors;

/**
 * Базовая ошибка для приложения. Все создаваемые ошибки наследовать от этого класса
 */
public abstract class VoyageError extends RuntimeException {

    /**
     * Сообщение об ошибке
     */
    protected String message;

    /**
     * Код ошибки
     */
    protected VoyageErrorCode errorCode;

    public VoyageError() { }

    public VoyageError(String message, VoyageErrorCode errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VoyageErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(VoyageErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
