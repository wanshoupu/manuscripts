do {
    ++s;
} while(s < e && *s < *pivot);

do {
    --e;
} while(pivot < e && *e > *pivot);
