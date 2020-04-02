package org.shoupu.string;

import java.util.Arrays;

public class ValidNumber {
    public boolean isNumber(String string) {
        String string1 = string.trim().toLowerCase();
        if (string1.contains("e")) {
            return isScientific(string1);
        }
        if (string1.contains(".")) {
            return isFloat(string1);
        }
        return isInteger(string1);
    }

    boolean isScientific(String s) {
        String[] parts = s.split("e", 2);
        return isFloat(parts[0]) && isInteger(parts[1]);
    }

    boolean isFloat(String s) {
        s = stripSign(s);
        String[] parts = s.split("\\.", 2);
        if (parts.length == 1) {
            return isUnsigned(parts[0]);
        }
        return Arrays.stream(parts).allMatch(p -> p.isEmpty() || isUnsigned(p));
    }

    String stripSign(String s) {
        return s.startsWith("+") || s.startsWith("-") ? s.substring(1) : s;
    }

    boolean isInteger(String s) {
        s = stripSign(s);
        return isUnsigned(s);
    }

    boolean isUnsigned(String s) {
        return s.matches("\\d+");
    }
}
