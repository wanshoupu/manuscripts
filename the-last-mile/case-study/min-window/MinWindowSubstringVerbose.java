public class MinWindowSubstringVerbose {
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
         * Note the pattern:
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
