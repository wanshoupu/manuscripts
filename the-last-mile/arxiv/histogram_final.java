public int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    stack.push(-1);
    int maxRec = 0;
    for (int i = 0; i <= heights.length; /*increment occurs in loop*/) {
        if (stack.peek() == -1 || i < heights.length && heights[i] > heights[stack.peek()]) {
            stack.push(i++);
        } else {
            int rec = heights[stack.pop()] * (i - 1 - stack.peek());
            maxRec = Math.max(maxRec, rec);
        }
    }
    return maxRec;
}
