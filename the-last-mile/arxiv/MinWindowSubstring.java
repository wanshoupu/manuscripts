public class MinWindowSubstring {

    /**
     * An implementation that uses sentinel, i.e.,
     * it creates a composite string was first constructed by concatenating the 'tgt' + 'src' string.
     * Then the algorithm is performed on the resultant string as usual with the caveat: avoid optimizing before the window leaves the prepened segment.
     * At the end, post-process the solution with nominal one-liner.
     *
     * @param src
     * @param tgt
     * @return
     */
    static
    public String minWindow(String src, String tgt) {
        //used to contain surplus but 'useful' chars for subsequent extension of window
        //Be careful not to introduce new keys after the initialization, as the keys in this repository is used to test for tgt characters
        Map<Character, Integer> repository = new HashMap<>();
        for (char c : tgt.toCharArray()) {
            repository.put(c, 0);
        }

        // add sentinel
        src = tgt + src;
        // The initial window is set to contain all the characters in tgt
        int minStart = tgt.length();
        int minEnd = src.length() + 1;
        for (int start = 0, end = tgt.length(); ; ) {
            //update the min-window. This isn't needed for all the if-else cases. But doesn't hurt to check
            if (start >= minStart && end - start < minEnd - minStart) {
                minStart = start;
                minEnd = end;
            }
            // The start is pruned one character at a time
            char c = src.charAt(start);
            // The repository is searched for the pruned character to
            // maintain the 'containing' invariance
            Integer count = repository.get(c);
            if (count == null) {
                //c is not needed
                ++start;
            } else if (count > 0) {
                //c is needed and we have it in repository
                repository.put(c, count - 1);
                ++start;
            } else if (end == src.length()) {
                //if c is needed but repository doesn't have it and no more unexplored string left, terminate
                break;
            } else {
                // explore more char to replenish repository: relevant chars are pushed to repository

                c = src.charAt(end);
                count = repository.get(c);
                if (count != null) {
                    repository.put(c, count + 1);
                }
                ++end;
            }
        }
        return minEnd <= src.length() ? src.substring(minStart, minEnd) : "";
    }
}
