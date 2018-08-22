package org.shoupu.codingAlgorithm;

import org.shoupu.utils.Union;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Encoding/decoding class of a list of strings.
 * Algorithm is based on base256 -> base64 + special chars such as NULL_LIST, NULL_STRING, EOS, etc.
 * To hide these special chars, we double-encode
 * During conversion between the two bases, we used a Union data structure
 */
public class VerboseListStringEncoder {

    private static final char BASE62 = '|';
    private static final char BASE63 = '=';

    //Will not appear in the final encoded string 
    //these special char must (best) be printable
    private static final String NULL_LIST = "#";
    private static final String EOS = "-"; //cannot be a regex special char, because we need to split based on this char using String.split() function
    private static final String NULL_STRING = "@";
    private static final int QUAD_BITSIZE = 6;
    private static final int QUAD_SIZE = 4;
    private static final int TRIPLE_SIZE = 3;
    private static final int TRIPLE_BITSIZE = 8;

    private static char[] base64Array = new char[64];
    private static HashMap<Character, Integer> base64Map = new HashMap<Character, Integer>();

    static {
        for (int i = 0; i < 64; ++i) {
            if (i < 10) {
                base64Array[i] = (char) ('0' + i);
            } else if (i < 36) {
                base64Array[i] = (char) ('A' + i - 10);
            } else if (i < 62) {
                base64Array[i] = (char) ('a' + i - 36);
            } else {
                base64Array[i] = i == 62 ? BASE62 : BASE63;
            }
            base64Map.put(base64Array[i], i);
        }
    }

    private static String encode(String str) {
        StringBuilder sb = new StringBuilder();
        char[] ca = str.toCharArray();
        for (int i = 0; i < ca.length; i += TRIPLE_SIZE) {
            char[] triple = new char[TRIPLE_SIZE];
            for (int j = 0; j < TRIPLE_SIZE && i + j < ca.length; ++j) {
                triple[j] = ca[i + j];
            }
            sb.append(encode(triple));
        }
        return sb.toString();
    }

    /**
     * To hide the special char NULL_LIST, NULL_STRING, etc, we do double-encode
     *
     * @param list
     * @return
     */
    public static String encode(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list == null)
            sb.append(NULL_LIST);
        else {
            for (String s : list) {
                if (s == null) {
                    sb.append(NULL_STRING);
                } else {
                    sb.append(encode(s));
                }
                sb.append(EOS);
            }
            //remove last EOS
            sb.deleteCharAt(sb.length() - 1);
        }
        return encode(sb.toString());
    }

    private static char[] decode1(char[] ca) {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < ca.length; x += QUAD_SIZE) {
            char[] quad = new char[QUAD_SIZE];
            for (int y = 0; y < QUAD_SIZE && x + y < ca.length; ++y) {
                quad[y] = ca[x + y];
            }
            char[] triple = decode(quad);
            sb.append(triple);
        }
        ca = sb.toString().toCharArray();
        //trim off padding, if any
        ca = trim(ca);
        return ca;
    }

    public static List<String> decode(String encoded) {
        char[] ca = decode1(encoded.toCharArray());
        encoded = new String(ca);
        if (encoded.equals(NULL_LIST))
            return null;
        String[] strs = encoded.split(EOS);
        for (int i = 0; i < strs.length; ++i) {
            if (strs[i].equals(NULL_STRING)) {
                strs[i] = null;
            } else {
                strs[i] = new String(decode1(strs[i].toCharArray()));

            }
        }
        return toList(strs);
    }

    private static char[] trim(char[] quad) {
        int size = 0;
        for (char c : quad) {
            if (c == 0)
                break;
            size++;
        }
        if (size == quad.length)
            return quad;
        char[] result = new char[size];
        System.arraycopy(quad, 0, result, 0, size);
        return result;
    }


    /**
     * Given a quad (four-char array), decode it into the original triple (three-char array)
     * <p>
     * For decode/encode, two frequently used operations are
     * a) split the bit set represented by a char into two parts
     * b) splice two bit sets into one char
     * bit operation
     *
     * @param quad
     * @return
     */
    protected static char[] decode(char[] quad) {
        assert (quad != null && quad.length == QUAD_SIZE);

        int[] bitset = new int[QUAD_SIZE];
        for (int i = 0; i < QUAD_SIZE; ++i) {
            bitset[i] = base64Map.get(quad[i]);
        }

        char[] decoded = new char[TRIPLE_SIZE];
        Union union = new Union(bitset, QUAD_BITSIZE);
        bitset = union.getArray(TRIPLE_BITSIZE);
        for (int i = 0; i < TRIPLE_SIZE; ++i) {
            decoded[i] = (char) bitset[i];
        }
        return decoded;
    }

    /**
     * Given a triple (three-char array), encode it into the quad (four-char array)
     *
     * @param triple
     * @return
     */
    protected static char[] encode(char[] triple) {
        assert (triple != null && triple.length == TRIPLE_SIZE);
        int[] bitset = new int[TRIPLE_SIZE];
        for (int i = 0; i < TRIPLE_SIZE; ++i) {
            bitset[i] = triple[i];
        }
        Union union = new Union(bitset, TRIPLE_BITSIZE);
        bitset = union.getArray(QUAD_BITSIZE);
        char[] encoded = new char[QUAD_SIZE];
        for (int i = 0; i < QUAD_SIZE; ++i) {
            encoded[i] = base64Array[bitset[i]];
        }
        return encoded;
    }

    private static List<String> toList(String[] strs) {
        List<String> list = new ArrayList<String>();
        for (String s : strs) {
            list.add(s);
        }
        return list;
    }

}
