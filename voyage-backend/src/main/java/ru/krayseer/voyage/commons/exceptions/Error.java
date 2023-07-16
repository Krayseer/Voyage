package ru.krayseer.voyage.commons.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error extends RuntimeException {
    
    public Error(String message) {
        super(message);
    }

}
