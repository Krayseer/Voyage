package ru.krayseer.accountservice.commons.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpHeaders;

import java.util.Objects;

public final class Utils {

    public static String getTokenFromHeader(HttpServletRequest request) {
        var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !Objects.equals(header, "") ? header.substring(7) : null;
    }

}
