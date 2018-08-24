public class MinWindowSubstringVerbose {
    public String minWindow(String src, String tgt) {
        //used to contain surplus but 'useful' chars for subsequent extension of window
        //Be careful not to introduce new keys after the initialization, as the keys in this repository is used to test for tgt characters
        Map<Character, Integer> repository = new HashMap<>();
        for (char c : tgt.toCharArray()) {
            repository.put(c, repository.getOrDefault(c, 0) - 1);
        }
        Set<Character> deficit = new HashSet<>(repository.keySet());
        int right = 0;
        for (; right < src.length() && !deficit.isEmpty(); ++right) {
            char c = src.charAt(right);
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

        int minLeft = 0;
        int minRight = right;
        /*
         * 1. The start-end window is initialized to contain all the characters in target
         * 2. The start is pruned one character at a time
         * 3. The repository is searched if the pruned character is a needed by the target to maintain the 'containing' invariance
         * 4. If repository doesn't have the needed character, substrings [end, ...] is searched one at a time
         * 5. All relevant chars, needed or surplus, are pushed to repository
         * Note the pattern:
         * Newly discovered characters are first pushed to repository and then be used to replace pruned 'start' as needed
         * Newly discovered characters at the 'end' are never directly used to supply to pruned 'start'.
         */

        for (int left = 0; ; ) {
            //update the min-window. This isn't needed for all the if-else cases. But doesn't hurt to check
            if (right - left < minRight - minLeft) {
                minLeft = left;
                minRight = right;
            }
            char c = src.charAt(left);
            Integer count = repository.get(c);
            if (count == null) {
                //c is not needed
                ++left;
            } else if (count > 0) {
                //c is needed and we have it in repository
                repository.put(c, count - 1);
                ++left;
            } else if (right == src.length()) {
                break;
            } else {
                //explore more char to replenish repository
                c = src.charAt(right);
                count = repository.get(c);
                if (count != null) {
                    repository.put(c, count + 1);
                }
                ++right;
            }
        }
        return src.substring(minLeft, minRight);
    }
}
