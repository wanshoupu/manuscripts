public int largestRectangleArea(int[] h) {
    Stack<Integer> stack = new Stack<Integer>();
    int maxRec = 0;
    for (int i = 0; i <= h.length; /*increment occurs in loop*/) {
        if (stack.empty() || i < h.length && h[i] > h[stack.peek()]) {
            stack.push(i);
            i++;
        } else {
            int height = stack.pop();
            //Calculate the maximum of the rectangles ending at the (i-1)th element
            maxRec = Math.max(maxRec, h[height] * (stack.empty() ? i : i - stack.peek() - 1));
        }
    }
    return maxRec;
}
