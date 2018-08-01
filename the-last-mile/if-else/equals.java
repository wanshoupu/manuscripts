@Override
public boolean equals(Object o){
    if(o == null) return false;
    if(!(o instanceof Pair)) return false;
    Pair p = (Pair)o;
    return this.j == p.j && this.i == p.i;
}
