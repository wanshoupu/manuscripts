int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxRec = 0;
    for (int i = 0; i < heights.length; ++i) {
        while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxRec = Math.max(maxRec, height * width);
        }
        stack.push(i);
    }
    while (!stack.isEmpty()) {
        int height = heights[stack.pop()];
        int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
        maxRec = Math.max(maxRec, height * width);
    }
    return maxRec;
}
