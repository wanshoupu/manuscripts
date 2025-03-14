void qsort(int* s, int* e) {
     if (e - s < 2) return;
     int* p = part(s, e);
     qsort(s, p);
     qsort(p + 1, e);
}
