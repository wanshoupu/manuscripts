func foo(i int) int {
    if i < 0 {
		log.Fatal()
    }else if y := fibonacci(i); y < 1.0e9 {
        return y
    } else {
        return 1.0e9
    }
    // y is unavailable here
    return 0
}
