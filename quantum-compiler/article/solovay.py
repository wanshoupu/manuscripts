def sk_decompose(self, U: NDArray, n: int) -> Bytecode:
    node, error = self.su2net.lookup(U)
    if n == 0 or np.isclose(error, 0, atol=self.rtol, rtol=self.atol):
        return node
    node = self._sk_decompose(U, n - 1)
    V, W = gc_decompose(U @ herm(node.data))
    vnode = self._sk_decompose(V, n - 1)
    wnode = self._sk_decompose(W, n - 1)
    children = [vnode, wnode, vnode.herm(), wnode.herm(), node]
    data = children[0].data @ children[1].data @ children[2].data @ children[3].data @ children[4].data
    return Bytecode(data, children=children)
