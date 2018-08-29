/**
 * http://www.akalin.cx/longest-palindrome-linear-time
 * O(N) solution with O(N) space
 * The idea is to augment the original char array by inserting some
 * 'blank' char as dummy palindromic center and restricting palindromic
 * search to odd-length palindromes only
 *
 * Accepted by OJ
 * @param str
 * @return
 */
public static String longestPalindrome(String str){
    int maxCenter = 0;
    //lengths stores the lengths of the longest palindrome around centers [0..n-1] in the original string
    int[] lengths = new int[str.length() * 2 + 1];
    for(int center = 0, j = 1; j < lengths.length; ++j){
        final int jmirror = 2 * center - j;
        if(j < center + lengths[center] && jmirror - lengths[jmirror] > center - lengths[center]){
                lengths[j] = lengths[jmirror];
        }else{
            //get the length of the longest palindrome centered on j
            for(int i = (center + lengths[center]) / 2; ; i++){
                /* one would find that the above initialization may be changed to "(center + lengths[center] + 1) / 2" without any difference
                 * This is because the value "(center + lengths[center])" is always even.
                 */
                if(j - i - 1 < 0 || i == str.length() || str.charAt(j - i - 1) != str.charAt(i)) {
                    lengths[j] = 2 * i - j;
                    break;
                }
            }
            center = j;
            maxCenter = lengths[maxCenter] < lengths[center] ? center : maxCenter;
        }
    } //end for
    //substring starts at (center - lengths[center]) / 2, and ends exclusively at (center + lengths[center]) / 2 beautifully!!
    return str.substring((maxCenter - lengths[maxCenter]) / 2, (maxCenter + lengths[maxCenter]) / 2);
}
