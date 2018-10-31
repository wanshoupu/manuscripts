if (abc != null && fooIsEmpty(abc, efg)) {
    // The situation is such that we can shortcircuit and return a false
    // because either abc doesn't exist or fooIsEmpty()
    return false;
}
/*
The logic is camouflaged within a ficticous problem setting
such that your reading will be hindered.
Now we return a true value
*/
return true;
