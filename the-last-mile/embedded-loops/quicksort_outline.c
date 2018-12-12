void qSort(int* s, int* e) {
     if (e - s < 2) return;
     int* p = part(s, e);
     qSort(s, p);
     qSort(p + 1, e);
}
