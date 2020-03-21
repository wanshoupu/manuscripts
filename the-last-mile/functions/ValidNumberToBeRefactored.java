package org.shoupu.string;

public class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        s = s.toLowerCase();
        if (s.contains("e")) {
            String[] parts = s.split("e", 2);
            if (parts[0].contains(".")) {
                String s1 = parts[1];
                if (s1.startsWith("+") || s1.startsWith("-")) {
                    s1 = s1.substring(1);
                }
                return isFloat(parts[0]) && s1.matches("\\d+");
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
        if (s.contains(".")) {
            return isFloat(s);
        }
        String s1 = s;
        if (s1.startsWith("+") || s1.startsWith("-")) {
            s1 = s1.substring(1);
        }
        return s1.matches("\\d+");
    }

    boolean isFloat(String s) {
        if (s.startsWith("+") || s.startsWith("-")) {
            s = s.substring(1);
        }
        String[] parts = s.split("\\.", 2);
        if (parts[0].isEmpty() && parts[1].isEmpty()) return false;
        return (parts[0].isEmpty() || parts[0].matches("\\d+"))
                && (parts[1].isEmpty() || parts[1].matches("\\d+"));
    }

}
