/**
 * The idea is to augment the original char array by inserting some
 * 'blank' char as dummy palindromic center and restricting palindromic
 * search to odd-length palindromes only
 */
public static String longestPalindrome(String str){
    final int n = str.length() * 2 + 1;
    char[] aug = new char[n];
    for(int i = 0; i < n; ++i){
        aug[i] = (i & 1) == 0 ? 0 : str.charAt(i / 2);
    }
    int maxStart = 0, maxEnd = 0;
    int[] length = new int[n];
    length[0] = 1;
    for(int center = 0, j = 1; j < n; ++j){
        int wing = length[center] / 2;
        if(j < center + wing){
            int jmirror = 2 * center - j;
            int jwing = length[jmirror] / 2;
            if(jmirror - jwing > center - wing){
                length[j] = length[jmirror];
                continue;
            }
        }
        //get the length of the longest palindrome centered on j
        for(int i = center + wing + 1; ; i++){
            if(2 * j - i < 0 || i == n || aug[2 * j - i] != aug[i]) {
                length[j] = 2 * (i - 1 - j) + 1;
                break;
            }
        }
        center = j;
        if(maxEnd - maxStart < length[center] / 2){
            maxStart = (center + 1 - length[center] / 2) / 2;
            maxEnd = (center + 1 + length[center] / 2) / 2;
        }
    } //end for
    return str.substring(maxStart, maxEnd);
}
