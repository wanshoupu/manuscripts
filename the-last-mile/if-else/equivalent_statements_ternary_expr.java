boolArr[i + 1] = indxOOB(boolArr, i) ? false : boolArr[i];

boolArr[i + 1] = !indxOOB(boolArr, i) && boolArr[i];
