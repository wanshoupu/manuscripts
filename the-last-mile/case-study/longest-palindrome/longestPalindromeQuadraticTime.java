    public class LongestPalindrome {
        /**
         * O(N^2) solution
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            int maxStart = 0, maxLength = 0;
            ArrayList<Integer> prevLengths = new ArrayList<Integer>();
            prevLengths.add(0);
            for(int i = 0; i < s.length(); ++i){
                ArrayList<Integer> lengths = new ArrayList<Integer>();
                for(int length : prevLengths){
                    if(i - 1 - length >= 0 && s.charAt(i - 1 - length) == s.charAt(i)){
                        lengths.add(length + 2);
                    }
                }
                lengths.add(1);
                lengths.add(0);
                if(lengths.get(0) > maxLength){
                    maxStart = i + 1 - lengths.get(0);
                    maxLength = lengths.get(0);
                }
                prevLengths = lengths;
            }

            return s.substring(maxStart, maxStart + maxLength);
        }

        /**
         * For linear time solution
         * http://www.akalin.cx/longest-palindrome-linear-time
         */
    }
