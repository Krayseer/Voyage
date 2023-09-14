package ru.krayseer.accountservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;

import java.util.Objects;

import static ru.krayseer.accountservice.commons.constants.SecurityConstants.INDEX_START_TOKEN_VALUE;

public final class Utils {

    public static String getTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !Objects.equals(header, "") ? header.substring(INDEX_START_TOKEN_VALUE) : null;
    }

}
