public String intToRoman (int num){
    StringBuilder sb = new StringBuilder();
    int d = 0;
    while (num != 0) {
        int t = num / ds[d];
        num %= ds[d];
        for (int j = 0; j < t; ++j) {
            sb.append(cs[d]);
        }
        ++d;
    }
    return sb.toString();
}
