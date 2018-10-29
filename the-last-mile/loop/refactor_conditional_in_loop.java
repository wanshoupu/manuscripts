public void execute(int key) {
    List<Node> list = ...
    Node target = null;
    for (Node n : list){
        if (n != null && n.val = key) {
            target = n;
        }
    }
    Object[] parameters = ...
    Machine machine = new Machine(target, parameters);
    machine.run();
}

public void execute(int key) {
    Object[] parameters = ...
    Machine machine = new Machine(lookup(key), parameters);
    machine.run();
}

private Node lookup(int key) {
    List<Node> list = ...
    for (Node n : list) {
        if (n != null && n.val = key) {
            return n;
        }
    }
}