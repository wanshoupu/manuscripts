import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardStringMatching {

    static class Match{
        public int start;
        public int end;
        String substr;
        public Match(int start, int end, String str){
            this.start = start;
            this.end = end;
            this.substr = str;
        }
    }
    
    /**
     * Pitfall:
     * 1. matched indexes can be easily messed up. Draw graph on board to help clarify thoughts;
     * 2. String match coding could be tricky. One way is to use while(true) or for(;;) and leave condition/terminatino checking inside the loop. I haven't found a better way to code.
     * Advanced requirement: 
     * 1. maximum length of matched string
     * 2. multiple matched substrings
     * @param text regular string to search for
     * @param pattern can contain regular char, '?' and '*' with '?' matches any single char and '*' matches any string of zero or non-zero length
     * @return the substring of the text that is matched to the pattern
     */
    public static Match[] findMatches(String text, String pattern){
        String[] blocks = pattern.split("\\*");
        List<String> blockList= new ArrayList<String>(Arrays.asList(blocks));
        List<Match> forwardMatches = new ArrayList<Match>();
        List<Match> backwardMatches = new ArrayList<Match>();
        int beginIndex = 0;
        int endIndex = text.length();
        do{
            String p = blockList.get(0);
            blockList.remove(0);
            Match m = findMatch(text, beginIndex, endIndex, p, false);
            if(m == null) return null;
            beginIndex = m.end;
            forwardMatches.add(m);
            if(!blockList.isEmpty()){
                int lastIndex = blockList.size() - 1;
                p = blockList.get(lastIndex);
                blockList.remove(lastIndex);
                m = findMatch(text,beginIndex, endIndex, p, true);
                if(m == null) return null;
                endIndex = m.start;
                backwardMatches.add(0, m);
            }
        }while(!blockList.isEmpty());
        forwardMatches.addAll(backwardMatches);
        return forwardMatches.toArray(new Match[0]);
    }
    
    private static Match findMatch(String text, int beginIndex, int endIndex, String pattern, boolean reversedSearch) {
        if(text == null || pattern == null || beginIndex < 0 || beginIndex > endIndex || endIndex > text.length())
            return null;
        if(reversedSearch){
            for(int r = endIndex - pattern.length(); ; --r){
                for(int e = 0; ; ++e){
                    if(r + e < beginIndex)
                        return null;
                    if(e == pattern.length())
                        return new Match(r, r + pattern.length(), pattern);
                    if(pattern.charAt(e) != '?' && text.charAt(r + e) != pattern.charAt(e))
                        break;
                }
            }
        }else{
            for(int r = beginIndex; ; ++r){
                for(int e = 0; true; ++e){
                    if(r + e == endIndex)
                        return null;
                    if(e == pattern.length())
                        return new Match(r, r + pattern.length(), pattern);
                    if(pattern.charAt(e) != '?' && text.charAt(r + e) != pattern.charAt(e)){
                        break;
                    }
                }
            }
        }
    }

    private static String toString(Match[] matches) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(Match m : matches){
            for(int a = i; a < m.start; ++a){
                sb.append('*');
            }
            sb.append(m.substr);
            i = m.end;
        }
        
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String text= "abceoasdghoidabcioeghxyzghasioeasabcxyz";
        System.out.println(text);

        Match m = findMatch(text, 0,text.length(), "?abc", false);
        Match mr = findMatch(text, 0,text.length(), "bc?oeg", true);
        if(m == null || mr == null){
            System.out.println("Test failed");
            return;
        }else{
            System.out.println(toString(new Match[]{m}));
            System.out.println(toString(new Match[]{mr}));
        }
        String[] patterns = {"abc*abc", "abc*abc*a*cxyz", "a?c*abc?", "a?c*abc*"};
        for(String p : patterns){
            Match[] result = findMatches(text, p );
            if(result == null){
                System.out.println("No match!");
                continue;
            }
            String matchedString = toString(result);
            System.out.println(p);
            System.out.println(text);
            System.out.println(matchedString);
        }
    }

}
