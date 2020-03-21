package org.shoupu.string;

public class ValidNumber {
    public class Solution {
        public boolean isNumber(String s) {
            s = s.trim();
            s = s.toLowerCase();
            if (s.contains("e")) {
                return isScientific(s);
            }
            if (s.contains(".")) {
                return isFloat(s);
            }
            return isInteger(s);
        }

        boolean isScientific(String s) {
            String[] parts = s.split("e", 2);
            if (parts[0].contains(".")) {
                return isFloat(parts[0]) && isInteger(parts[1]);
            } else {
                return isInteger(parts[0]) && isInteger(parts[1]);
            }
        }

        boolean isFloat(String s) {
            if (s.startsWith("+") || s.startsWith("-")) {
                s = s.substring(1);
            }
            String[] parts = s.split("\\.", 2);
            if (parts[0].isEmpty() && parts[1].isEmpty()) return false;
            return (parts[0].isEmpty() || isUnsigned(parts[0]))
                    && (parts[1].isEmpty() || isUnsigned(parts[1]));
        }

        boolean isInteger(String s) {
            if (s.startsWith("+") || s.startsWith("-")) {
                s = s.substring(1);
            }
            return isUnsigned(s);
        }

        boolean isUnsigned(String s) {
            if (s.isEmpty()) return false;
            for (char c : s.toCharArray()) {
                if (c < '0' || c > '9') return false;
            }
            return true;
        }
    }

    public boolean isNumber(String s) {
        s = s.trim().toLowerCase();
        if (s.isEmpty()) return false;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            s = s.substring(1);
        }
        return isScientific(s);
    }

    boolean isScientific(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.contains("e")) {
            String[] parts = s.split("e", 2);
            return isDotFloat(parts[0]) && isInteger(parts[1]);
        }
        return isDotFloat(s);
    }

    boolean isDotFloat(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.startsWith(".")) {
            return isUnsignedInteger(s.substring(1));
        }
        if (s.endsWith(".")) {
            return isUnsignedInteger(s.substring(0, s.length() - 1));
        }
        if (s.contains(".")) {
            String[] parts = s.split("\\.", 2);
            return isUnsignedInteger(parts[0]) && isUnsignedInteger(parts[1]);
        }
        return isUnsignedInteger(s);
    }

    boolean isInteger(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            s = s.substring(1);
        }
        return isUnsignedInteger(s);

    }

    boolean isUnsignedInteger(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}