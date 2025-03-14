package org.shoupu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PathSimplifier {
    static
    public class Solution {
        static
        public String simplifyPath(String path) {
            List<String> links = simplify(parse(path));
            if (links.isEmpty()) {
                return "/";
            }
            StringBuilder sb = new StringBuilder();
            for (String dir : links) {
                sb.append("/").append(dir);
            }
            return sb.toString();
        }

        static List<String> simplify(Iterable<String> links) {
            Stack<String> result = new Stack<String>();
            for (String dir : links) {
                if ("..".equals(dir)) {
                    if (!result.isEmpty()) {
                        result.pop();
                    }
                } else {
                    result.push(dir);
                }
            }

            return result;
        }

        static Iterable<String> parse(String path) {
            List<String> links = new ArrayList<String>();
            for (String p : path.split("/")) {
                if (!p.isEmpty() && !".".equals(p)) {
                    links.add(p);
                }
            }
            return links;
        }
    }

    static
    public class Solution2 {
        static
        public String simplifyPath(String path) {
            Stack<String> links = parse(path);
            links = simplify(links);
            if (links.isEmpty()) {
                return "/";
            }
            StringBuilder sb = new StringBuilder();
            while (!links.isEmpty()) {
                sb.append("/").append(links.pop());
            }
            return sb.toString();
        }

        static Stack<String> simplify(Stack<String> links) {
            Stack<String> reverseLinks = new Stack<String>();
            for (int pop = 0; !links.isEmpty(); ) {
                String dir = links.pop();
                if ("..".equals(dir)) {
                    ++pop;
                } else if (pop > 0) {
                    --pop;
                } else {
                    reverseLinks.push(dir);
                }
            }
            return reverseLinks;
        }

        static Stack<String> parse(String path) {
            Stack<String> links = new Stack<String>();
            for (String p : path.split("/")) {
                if (!p.isEmpty() && !".".equals(p)) {
                    links.push(p);
                }
            }
            return links;
        }
    }

    public class Solution1 {
        public String simplifyPath(String path) {
            if (path == null || path.isEmpty()) {
                return "/";
            }
            String[] parts = path.split("/");
            Stack<String> stack = new Stack<String>();
            for (String part : parts) {
                /* Bug: Don't use this
                 *  if(part.equals("..") && !stack.isEmpty())
                 *      stack.pop();
                 * This will cause logic error as the subsequent else if condition will be evaluated erroneously.
                 */
                part = part.trim();
                if (part.isEmpty() || part.equals(".")) {
                    //handle cases such as "//", "/ /"
                    //do nothing
                } else if (part.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(part);
                }
            }
            if (stack.isEmpty()) {
                return "/";
            }

            parts = new String[stack.size()];
            for (int i = parts.length; i > 0; ) {
                parts[--i] = stack.pop();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parts.length; ++i) {
                sb.append("/").append(parts[i]);
            }
            return sb.toString();
        }
    }

    public String simplifyPath(String path) {
        return Solution.simplifyPath(path);
    }

}