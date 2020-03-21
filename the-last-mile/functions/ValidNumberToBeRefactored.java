package org.shoupu.string;

import java.util.Arrays;

public class ValidNumber {
    public boolean isNumber(String string) {
        String string1 = string.trim().toLowerCase();
        if (string1.contains("e")) {
            String[] parts = string1.split("e", 2);
            if (parts[0].contains(".")) {
                String s1 = parts[1];
                if (s1.startsWith("+") || s1.startsWith("-")) {
                    s1 = s1.substring(1);
                }
                if (!s1.matches("\\d+")) return false;
                String s0 = parts[0];
                if (s0.startsWith("+") || s0.startsWith("-")) {
                    s0 = s0.substring(1);
                }
                String[] parts1 = s0.split("\\.", 2);
                if (parts1.length == 1) {
                    return parts1[0].matches("\\d+");
                }
                return Arrays.stream(parts1).allMatch(p -> p.isEmpty() || p.matches("\\d+"));
            } else {
                String s1 = parts[1];
                if (s1.startsWith("+") || s1.startsWith("-")) {
                    s1 = s1.substring(1);
                }
                String s2 = parts[0];
                if (s2.startsWith("+") || s2.startsWith("-")) {
                    s2 = s2.substring(1);
                }
                return s2.matches("\\d+") && s1.matches("\\d+");
            }
        }
        if (string1.contains(".")) {
            if (string1.startsWith("+") || string1.startsWith("-")) {
                string1 = string1.substring(1);
            }
            String[] parts = string1.split("\\.", 2);
            if (parts.length == 1) {
                return parts[0].matches("\\d+");
            }
            return Arrays.stream(parts).allMatch(p -> p.isEmpty() || p.matches("\\d+"));
        }
        String s1 = string1;
        if (s1.startsWith("+") || s1.startsWith("-")) {
            s1 = s1.substring(1);
        }
        return s1.matches("\\d+");
    }
}
