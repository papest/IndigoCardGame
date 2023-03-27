fun f(n: Int): Int {
    if (n == 0) return 4
    if (n == -1) return 1
    return f(n - 1) / 2 + 2 * f(n - 2)
}

fun main() {
    val n = readLine()!!.toInt()
    print(f(n))
}