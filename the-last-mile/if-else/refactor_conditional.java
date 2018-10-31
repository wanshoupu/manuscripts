public void execute(int id) {
    Animal animal = null;
    if (lookup(id).name == "Elephant" ) {
        System.out.printf("%n");
    } else if () {

    }
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