/**
 * Construct a complex conditional statement that necessitates
 * 1. non-linear stepping
 * 2.
 *
 */
for (int i = 0; i < s.length();) {
    if (!map.containsKey(s.charAt(i))) {
        ++i;
    }
    if (s.charAt(i) == 'x') {
        doSomethingWith(i);
    }
}