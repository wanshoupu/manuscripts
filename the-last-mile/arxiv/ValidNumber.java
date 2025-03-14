package org.shoupu.string;

public class ValidNumber {
    public class Solution {
        public boolean isNumber(String s) {
            //bug: forgot trimming
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
            //bug: forgot integer case
            //bug: forgot testing if first part contains "." before sending for float test
            if (parts[0].contains(".")) {
                return isFloat(parts[0]) && isInteger(parts[1]);
            } else {
                return isInteger(parts[0]) && isInteger(parts[1]);
            }
        }

        /**
         * It's tempting to remove the sign testing. But that'll be buggy for valid case "-.123"
         * It's tempting to do smart things like: isInteger(parts[0] + parts[1]). But this will render ".-1" being valid number!
         * One can be out-smart by himself by isInteger(parts[0] + "0" + parts[1]). But this will render "." being valid number!
         * What it really means is that:
         * a. not both parts can be empty (e.g. "." is not valid) and
         * b. if the parts[0] is not empty, it must be integer (e.g. "-1" is valid but "1-", "-" are not) and
         * c. if the part[1] is not empty it must be unsigned integer (e.g. "", "1")
         */
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
            //bug: forgot testing emptiness
            if (s.isEmpty()) return false;
            for (char c : s.toCharArray()) {
                if (c < '0' || c > '9') return false;
            }
            return true;
        }
    }

    /**
     * bug:
     * 1. didn't check string emptiness resulted in runtime error
     * 2. left sign checking to later which caused recursive problems.
     * Sign is a one time thing, so don't allow its checking in a recursive function
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        s = s.trim().toLowerCase();
        if (s.isEmpty()) return false;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            s = s.substring(1);
        }
        return isScientific(s);
    }

    /**
     * Bug:
     * 1. Incorrect checking on the overall sign of the double which resulted in incorrect check on +-1 or --1
     *
     * @param s
     * @return
     */
    private boolean isScientific(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.contains("e")) {
            String[] parts = s.split("e", 2);
            return isDotFloat(parts[0]) && isInteger(parts[1]);
        }
        return isDotFloat(s);
    }

    /**
     * bug:
     * 1. split function takes a regex A dot '.' is considered a wildcard char
     * 2. split result will automatically discard empty strings, if any
     * 3. lack of special treatment of empty decimal part or integral part: s.startsWith() and s.endsWith()
     * 4. Be careful with the choice of
     * if .. else if .. else if .. else structure
     * vs.
     * if {} if{} if{}
     * 5. Don't mix non-recursive if statement with recursive if statements which can lead to very buggy code
     *
     * @param s
     * @return
     */
    private boolean isDotFloat(String s) {
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

    private boolean isInteger(String s) {
        if (s.isEmpty()) {
            return false;
        }
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            s = s.substring(1);
        }
        return isUnsignedInteger(s);

    }

    /**
     * bug:
     * 1. empty string
     * 1. incorrect use of regex-matching s.matches("\\D")
     *
     * @param s
     * @return
     */
    private boolean isUnsignedInteger(String s) {
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