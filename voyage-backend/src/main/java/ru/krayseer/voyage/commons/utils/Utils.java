package ru.krayseer.voyage.commons.utils;

import jakarta.servlet.http.HttpServletRequest;

public final class Utils {

    public static String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }

}
