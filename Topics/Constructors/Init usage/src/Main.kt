fun main() {
    val timerValue = readLine()!!.toInt()
    val timer = ByteTimer(timerValue)
    println(timer.time)
}

const val MIN_TIME = -128
const val MAX_TIME = 127

data class ByteTimer(var time: Int) {
    init {
        time = if (time < MIN_TIME) MIN_TIME else if (time > MAX_TIME) MAX_TIME else time
    }
}