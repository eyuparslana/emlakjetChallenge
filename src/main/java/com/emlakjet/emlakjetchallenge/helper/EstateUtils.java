package com.emlakjet.emlakjetchallenge.helper;

public class EstateUtils {
    public static boolean paramIsNumeric(String param) {
        try {
            Integer.parseInt(param);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
