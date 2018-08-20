package org.shoupu.string;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the
 * characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum
 * window in S.
 */
public class MinWindowSubstring2018 {
    static class ModularSolution {
        /**
         * A greedy algorithm implementation
         * This implementation separates the Revenue and expenditure accounts into the if..else..
         *
         * It moves like a caterpillar: the push starts from the back.
         * It contains the idea of queuing up tasks to be processed later while being blocked.
         * So it's like an asynchronous way of processing.
         *
         * It stores the character deficit in a map.
         * It start with an open set-a set of indexes that are not sufficient to cover all the
         * deficit.
         * It counts the length of an open set as infinity.
         * It attempts to compress the length of the set by attempting replacing the most lagging
         * behind position with the next available position for the char.
         *
         * deficit functions as a counter of deficit, surplus, and an account of what character
         * is needed. But the exact original number is not needed while in the process.
         *
         * Aside from the deficit and the queue, we also need an integer to record the number of
         * unique characters that is still in want.
         *
         * @param str
         * @param target
         * @return
         */
        static
        public String minWindow(String str, String target) {
            //used to contain surplus but 'useful' chars for subsequent extension of window
            //Be careful not to introduce new keys after the initialization, as the keys in this repository is used to test for target characters
            Map<Character, Integer> repository = new HashMap<>();
            for (char c : target.toCharArray()) {
                repository.put(c, 0);
            }

            str = target + str;
            int minStart = target.length(); // inclusive
            int minEnd = str.length(); // inclusive
            /*
             * 1. The start-end window is initialized to contain all the characters in target
             * 2. The start is pruned one character at a time
             * 3. The repository is searched if the pruned character is a needed by the target to maintain the 'containing' invariance
             * 4. If repository doesn't have the needed character, substrings [end, ...] is searched one at a time
             * 5. All relevant chars, needed or surplus, are pushed to repository
             * Note the pattern: newly char --> repo --> 'start'
             * Newly discovered characters are first pushed to repository and then be used to replace pruned 'start' as needed
             * Newly discovered characters at the 'end' are never directly used to supply to pruned 'start'.
             */
            for (int start = 0, end = target.length() - 1; start < str.length() && end < str.length(); ) {
                //update the min-window.
                if (start >= target.length() && end - start < minEnd - minStart) {
                    minStart = start;
                    minEnd = end;
                }
                // Consume a char from repository and if successful, find next relevant char
                if (consumeChar(str.charAt(start), repository)) {
                    start = nextIndex(start, str, repository);
                }
                // find the next relevant char and if successful recruit it to repository
                else if ((end = nextIndex(end, str, repository)) < str.length()) {
                    recruitChar(str.charAt(end), repository);
                }
            }
            return minEnd < str.length() ? str.substring(minStart, minEnd + 1) : "";
        }

        private static boolean consumeChar(char c, Map<Character, Integer> repository) {
            Integer count = repository.get(c);
            if (count > 0) {
                //c is needed and we have it in repository
                repository.put(c, count - 1);
                return true;
            }
            return false;
        }

        private static int nextIndex(int index, String str, Map<Character, Integer> repository) {
            do {
                ++index;
            } while (index < str.length() && !repository.containsKey(str.charAt(index)));
            return index;
        }

        private static void recruitChar(char endChar, Map<Character, Integer> repository) {
            repository.put(endChar, repository.get(endChar) + 1);
        }
    }

    static class Solution1 {
        /**
         * This implementation didn't get completed because I realized that it is defective and
         * need to break the for(int i...) step-by-step auto increment and stop for house-keeping
         * tasks. It is so because of its similarity to asynchronous nature.
         *
         * @param str
         * @param target
         * @return
         */
        static String minWindow(String str, String target) {
            Map<Character, Integer> surplus = countChar(target);
            int deficiency = surplus.size();
            Queue<Integer> indexes = new ArrayDeque<>();
            int start = 0, end = -1;
            for (int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (surplus.containsKey(c)) continue;

                indexes.offer(i);
                surplus.put(c, surplus.get(c) + 1);
                if (surplus.get(c) >= 0) {
                    --deficiency;
                }
                if (str.charAt(indexes.peek()) == c && surplus.get(c) > 0) {
                    indexes.poll();
                    surplus.put(c, surplus.get(c) - 1);
                }

            }
            return null;
        }

        private static Map<Character, Integer> countChar(String target) {
            return new HashMap<>();
        }
    }

    public static void main(String[] args) {
        String[] tests = {
                "abcdebdde",
                "bde",
                "CADOBECODwxyEBANABC",
                "ABCC",
                "ADOBECODEBANCoooooo",
                "ABC",
                "ADOBECODEBoooANC",
                "ABC",
        };
        for (int i = 0; i < tests.length; i += 2) {
            String result = ModularSolution.minWindow(tests[i], tests[i + 1]);
            System.out.format("'%s' in '%s' contains '%s'%n", result, tests[i], tests[i + 1]);
        }
    }

}
