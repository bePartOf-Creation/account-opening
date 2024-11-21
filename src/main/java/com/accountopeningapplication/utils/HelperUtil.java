package com.accountopeningapplication.utils;

import java.security.SecureRandom;
import java.util.Random;

public class HelperUtil {

    public static String generateRandomNumber(int length) {
        StringBuilder result = new StringBuilder();
        String characters = "2213456789";
        int charactersLength = characters.length();
        var random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(charactersLength)));
        }

        return result.toString();
    }

    public static String getTxRef(String txRefPrefix) {
        String domainRefNo = generateRandomAlphaNumVal(12);
        return txRefPrefix.concat(domainRefNo);
    }

    public static String generateRandomAlphaNumVal(int size) {

        SecureRandom random = new SecureRandom();
        StringBuilder requestIdBuffer = new StringBuilder();
        String[] requestIdArrayElement = new String[size];
        String[] valueElement = {"0","1","2","3","4","5","6","7","8","9", "a", "A", "b", "B", "c", "C", "d", "D", "e",
                "E", "f", "F", "g", "G", "h", "H", "i", "I", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O",
                "p", "P", "q", "Q", "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z", "Z"};

        for (int i=0; i < requestIdArrayElement.length; i++) {
            final int index = random.nextInt(valueElement.length);
            final String randVal = valueElement[index];
            requestIdBuffer.append(randVal);
        }

        return requestIdBuffer.toString();

    }
}
