for(int i1 = 0, i2 = 0; i1 < m || i2 < m && h2 != null; ++i1, ++i2){
    if(i2 == m || h2 == null || h1.val < h2.val){
        segHead.next = h1;
        h1 = h1.next;
    }else{
        segHead.next = h2;
        h2 = h2.next;
    }
}
