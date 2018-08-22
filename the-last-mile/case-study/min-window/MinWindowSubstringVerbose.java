package org.shoupu.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class MinWindowSubstringVerbose {
    static
    public class Solution {
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
                repository.put(c, repository.getOrDefault(c, 0) - 1);
            }
            Set<Character> deficit = new HashSet<>(repository.keySet());
            int end = 0;
            for (; end < str.length() && !deficit.isEmpty(); ++end) {
                char c = str.charAt(end);
                if (repository.containsKey(c)) {
                    repository.put(c, repository.get(c) + 1);
                    if (repository.get(c) >= 0) {
                        deficit.remove(c);
                    }
                }
            }
            if (!deficit.isEmpty()) {
                return "";
            }

            int minStart = 0;
            int minEnd = end;
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
            for (int start = 0; ; ) {
                //update the min-window. This isn't needed for all the if-else cases. But doesn't hurt to check
                if (end - start < minEnd - minStart) {
                    minStart = start;
                    minEnd = end;
                }
                char c = str.charAt(start);
                Integer count = repository.get(c);
                if (count == null) {
                    //c is not needed
                    ++start;
                } else if (count > 0) {
                    //c is needed and we have it in repository
                    repository.put(c, count - 1);
                    ++start;
                } else if (end == str.length()) {
                    break;
                } else {
                    //explore more char to replenish repository
                    c = str.charAt(end);
                    count = repository.get(c);
                    if (count != null) {
                        repository.put(c, count + 1);
                    }
                    ++end;
                }
            }
            return str.substring(minStart, minEnd);
        }
    }

    public static void main(String[] args) {
        String[] tests = {
                "bba",
                "ab",
                "ADOBECODEBANC",
                "ABC",
                "CADOBECODwxyEBANABC",
                "ABCC",
                "ADOBECODEBANCoooooo",
                "ABC",
                "ADOBECODEBoooANC",
                "ABC",
        };
        for (int i = 0; i < tests.length; i += 2) {
            String result = Solution.minWindow(tests[i], tests[i + 1]);
            System.out.format("'%s' in '%s' contains '%s'%n", result, tests[i], tests[i + 1]);
        }
    }

}
