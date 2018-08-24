public class ModularSolution {
    /**
     * A modular implementation of the minimum containing window algorithm
     *
     * @param src
     * @param tgt
     * @return
     */
    public String minWindow(String src, String tgt) {
        //used to contain surplus but 'useful' chars for subsequent extension of window
        Map<Character, Integer> repository = new HashMap<>();
        for (char c : tgt.toCharArray()) {
            repository.put(c, 0);
        }

        src = tgt + src;
        // The initial window is set to contain all the characters in tgt
        int minStart = tgt.length(); // inclusive
        int minEnd = src.length(); // inclusive
        for (int start = 0, end = tgt.length() - 1; start < src.length() && end < src.length(); ) {
            //update the min-window.
            if (start >= tgt.length() && end - start < minEnd - minStart) {
                minStart = start;
                minEnd = end;
            }
            // Consume a char from repository and if successful, find next relevant char
            if (consumeChar(src.charAt(start), repository)) {
                start = nextIndex(start, src, repository);
            }
            // find the next relevant char and if successful recruit it to repository
            else if ((end = nextIndex(end, src, repository)) < src.length()) {
                recruitChar(src.charAt(end), repository);
            }
        }
        return minEnd < src.length() ? src.substring(minStart, minEnd + 1) : "";
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
