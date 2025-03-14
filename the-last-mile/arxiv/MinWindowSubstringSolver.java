public class MinWindowSubstringSolver {
    private String src;
    private String tgt;
    private Map<Character, Integer> repository = new HashMap<>();

    public String minWindow(String src, String tgt) {
        // add sentinel
        src = tgt + src;
        this.src = src;
        this.tgt = tgt;
        repository.clear();
        //used to contain surplus but 'useful' chars for subsequent extension of window
        //Be careful not to introduce new keys after the initialization, as the keys in this repository is used to test for tgt characters
        for (char c : tgt.toCharArray()) {
            repository.put(c, 0);
        }

        return solve();
    }

    private String solve() {
        int minLeft = tgt.length(); // inclusive
        int minRight = src.length(); // inclusive
        for (int left = 0, right = tgt.length() - 1; left < src.length() && right < src.length(); ) {
            //attempt to update the min-window if window is disjoint with sentinel prefix
            if (left >= tgt.length() && right - left < minRight - minLeft) {
                minLeft = left;
                minRight = right;
            }
            // Consume a char from repository and if successful, find next relevant char
            if (tryPrune(src.charAt(left))) {
                left = nextIndex(left);
            } else if ((right = nextIndex(right)) < src.length()) {
                // find the next relevant char
                // if successful recruit it to repository
                recruitChar(src.charAt(right));
            }
        }
        return minRight < src.length() ? src.substring(minLeft, minRight + 1) : "";
    }

    /**
     * Attempt to find and consume the char from {@link #repository}
     *
     * @param c char to be searched for and consumed from {@link #repository}
     * @return true if successfully consumed, false otherwise
     */
    private boolean tryPrune(char c) {
        Integer count = repository.get(c);
        if (count > 0) {
            //c is needed and we have it in repository
            repository.put(c, count - 1);
            return true;
        }
        return false;
    }

    /**
     * Increment the index until a usable char is found in {@link #repository}
     *
     * @param index original position of the index
     * @return the resulting index pointing at a usable char
     */
    private int nextIndex(int index) {
        do {
            ++index;
        } while (index < src.length() && !repository.containsKey(src.charAt(index)));
        return index;
    }

    /**
     * Put a char in {@link #repository}
     *
     * @param c char to be put in {@link #repository}
     */
    private void recruitChar(char c) {
        repository.put(c, repository.get(c) + 1);
    }
}
