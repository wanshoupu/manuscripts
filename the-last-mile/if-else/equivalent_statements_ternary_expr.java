boolArr[j] =
  indxOOB(boolArr, i) ?
      false : boolArr[i];

boolArr[j] =
  !indxOOB(boolArr, i) &&
    boolArr[i];
