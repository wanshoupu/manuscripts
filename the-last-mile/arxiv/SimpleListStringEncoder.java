package org.shoupu.codingAlgorithm;

import java.util.ArrayList;
import java.util.List;


/**
 * Encoding/decoding class of a list of strings.
 * Algorithm is based on base256 -> base64 + special chars such as NULL_LIST, NULL_STRING, EOS, etc.
 * To hide these special chars, we double-encode
 * During conversion between the two bases, we used a Union data structure
 */
public class SimpleListStringEncoder {

    public static final int FIXED_WIDTH = 8;

    public static String encode(List<String> list) {
        if (list == null) {
            return "null";
        }
        StringBuilder sw = new StringBuilder();
        for (String s : list) {
            if (s == null) {
                // use -1 as a sentinel
                sw.append(toFixedWidth(-1));
            } else {
                sw.append(toFixedWidth(s.length()));
                sw.append(s);
            }
        }
        return sw.toString();
    }

    static String toFixedWidth(int i) {
        String hexString = Integer.toHexString(i);
        if (hexString.length() < FIXED_WIDTH) {
            String FORMAT = String.format("%%0%dd", FIXED_WIDTH - hexString.length());
            return String.format(FORMAT, 0) + hexString;
        } else {
            return hexString;
        }
    }

    static int parseInt(String s) {
        return Integer.parseUnsignedInt(s, 16);
    }

    public static List<String> decode(String encoded) {
        if (encoded.equals("null")) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < encoded.length(); ) {
            int size = parseInt(encoded.substring(i, i + FIXED_WIDTH));
            if (size < 0) {
                result.add(null);
                i += 8;
            } else {
                result.add(encoded.substring(i + 8, i + 8 + size));
                i += 8 + size;
            }
        }
        return result;
    }
}
