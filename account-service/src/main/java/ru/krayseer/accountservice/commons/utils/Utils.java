package ru.krayseer.accountservice.commons.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;

public final class Utils {

    public static String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
    }

}
