public String intToRoman ( int num){
    StringBuilder sb = new StringBuilder();
    for (int d = 0; num > 0; ) {
        if (num >= ds[d]) {
            num -= ds[d];
            sb.append(cs[d]);
        } else {
            ++d;
        }
    }
    return sb.toString();
}
