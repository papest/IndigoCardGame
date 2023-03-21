package indigo

val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
val suits = listOf("♦", "♥", "♠", "♣")

fun main() {
    println(ranks.joinToString(" "))
    println(suits.joinToString(" "))
    for (i in suits.indices) print("${ranks.joinToString("${suits[i]} ")}${suits[i]} ")
}