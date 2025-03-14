public static void iterative_dfs(Map<Integer, List<Integer>> graph){
    Stack<Integer> stack = new Stack<Integer>();
    for(int u : graph.keySet()){
        if(discoverTimes[u] == 0){
            //parents[u] = 0
            discoverTimes[u] = ++time;
            stack.push(u);
            do {
                int v = stack.peek();
                if(discoverTimes[v] != 0){
                    finishTimes[v] = ++time;
                    stack.pop();
                }
                for(int w : graph.get(v)){
                    if(discoverTimes[w] == 0){
                        parents[w] = v;
                        discoverTimes[w] = ++time;
                        stack.push(w);
                    }
                }
            }while(!stack.isEmpty());
        }
    }
}
