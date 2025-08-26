def sk_decompose(U: NDArray, n: int) -> Bytecode:
    node, error = su2net.lookup(U)
    if n == 0 or np.isclose(error, 0, atol=rtol, rtol=atol):
        return node
    node = sk_decompose(U, n - 1)
    V, W = gc_decompose(U @ herm(node.data))
    vnode = sk_decompose(V, n - 1)
    wnode = sk_decompose(W, n - 1)
    children = [vnode, wnode, vnode.herm(), wnode.herm(), node]
    data = (children[0].data @ children[1].data @ children[2].data
            @ children[3].data @ children[4].data)
    return Bytecode(data, children=children)
