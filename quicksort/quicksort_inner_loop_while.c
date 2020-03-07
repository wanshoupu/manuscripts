++s;
while(s < e && *s < *pivot) {
    ++s;
}
--e;
while(pivot < e && *e > *pivot) {
    --e;
}
