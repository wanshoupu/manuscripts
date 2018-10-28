void foo() {
    List<Node> list = ...
    Node target = null;
    for (Node n : list){
        if (n != null && n.val = key) {
            target = n;
        }
    }
    composite = new Composite(target, somethingElse);
    composite.run();
}