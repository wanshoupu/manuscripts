@Override
public boolean equals(Object obj){
    if(obj == null) return false;
    if(!(obj instanceof MyClass)) return false;
    MyClass that = (MyClass)obj;
    // assume integers j and i are member variables of MyClass
    return this.j == that.j && this.i == that.i;
}
