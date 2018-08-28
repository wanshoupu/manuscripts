public class LongestPalindrome {
    /**
     * Fail to pass test cases like "bananas"
     * @param str
     * @return
     */
    public static int longestPalindrome(String str){
        int[] len = new int[str.length()];
        len[0] = 1;
        int maxLength = 1;
        //starting index of the longest palindrome
        int maxIndex = 0; //one char palindrome at index 0
        for(int i = 1; i < str.length(); ++i){
            if(i - len[i - 1] - 1 >= 0 && str.charAt(i - len[i - 1] - 1) == str.charAt(i))
                len[i] = len[i - 1] + 2;
            else if(str.charAt(i - 1) == str.charAt(i))
                len[i] = 2;
            else
                len[i] = 1;
            if(maxLength < len[i]){
                maxLength = len[i];
                maxIndex = i - maxLength + 1;
            }
        }
        System.out.println(str.substring(maxIndex, maxIndex + maxLength));
        return maxLength;
    }
}
