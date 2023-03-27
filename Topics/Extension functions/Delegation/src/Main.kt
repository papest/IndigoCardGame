fun next(prev: Int): Int = prev * 1000 - 10
fun Int.nextValue() = next(this)