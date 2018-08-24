public class ModularSolution {
    /**
     * A modular implementation of the minimum containing window algorithm
     *
     * @param str
     * @param target
     * @return
     */
    public String minWindow(String str, String target) {
        //used to contain surplus but 'useful' chars for subsequent extension of window
        //Be careful not to introduce new keys after the initialization, as the keys in this repository is used to test for target characters
        Map<Character, Integer> repository = new HashMap<>();
        for (char c : target.toCharArray()) {
            repository.put(c, 0);
        }

        str = target + str;
        // The initial window is set to contain all the characters in target
        int minStart = target.length(); // inclusive
        int minEnd = str.length(); // inclusive
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
