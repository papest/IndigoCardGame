package indigo

val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
val suits = listOf("♦", "♥", "♠", "♣")
val deck = buildList {
    for (i in suits) {
        for (j in ranks) add("$j$i")
    }
}
var currentDeck = deck.toMutableList()

fun main() {
    menu()
    println("Bye")
}

fun get() {
    println("Number of cards:")
    val number: Int
    try {
        number = readln().toInt()
    } catch (e: Exception) {
        println("Invalid number of cards.")
        return
    }
    if (number !in 1..deck.size) {
        println("Invalid number of cards.")
        return
    }
    if (currentDeck.size < number) {
        println("The remaining cards are insufficient to meet the request.")
        return
    }
    for (i in 1..number) print("${currentDeck.removeFirst()} ")
    println()
}

fun shuffle() {
    currentDeck.shuffled()
    println("Card deck is shuffled.")
}

fun reset() {
    currentDeck = deck.toMutableList()
    println("Card deck is reset.")
}

fun menu() {
    while (true) {
        println("Choose an action (reset, shuffle, get, exit):")
        when (readln()) {
            "reset" -> reset()
            "shuffle" -> shuffle()
            "get" -> get()
            "exit" -> break
            else -> println("Wrong action.")
        }
    }
}
