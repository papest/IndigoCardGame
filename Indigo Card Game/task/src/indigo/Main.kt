package indigo

var cardsInHand = mutableListOf<String>()
var computerCards = mutableListOf<String>()
var cardsOnTable = mutableListOf<String>()
val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
val suits = listOf("♦", "♥", "♠", "♣")
val deck = buildList {
    for (i in suits) {
        for (j in ranks) add("$j$i")
    }
}
const val TITLE = "Indigo Card Game"
const val  INIT_TABLE = 4
const val INIT_HAND = 6
var currentDeck = deck.toMutableList()
var play = true
var userTurn = true

fun main() {
    play()
}

fun play() {
    println(TITLE)
    initGame()

    while (play) {
        turn()
    }

    println("Game Over")
}

fun initGame() {
    playFirst()
    shuffle()
    cardsOnTable = get(INIT_TABLE)
    println("Initial cards on the table: ${cardsOnTable.joinToString(" ")}")
    cardsInHand = get(INIT_HAND)
    computerCards = get(INIT_HAND)
    shuffle()
}

fun turn() {
    println("${cardsOnTable.size} cards on the table, and the top card is ${cardsOnTable.last()}")
    if (cardsOnTable.size == deck.size) {
        play = false
        return
    }
    if (userTurn) {
        if (cardsInHand.isEmpty()) cardsInHand = get(INIT_HAND)
        print("Cards in hand: ")
        (0 until cardsInHand.size).forEach {
            print("${it + 1})${cardsInHand[it]} ")
        }
        println()
        while (true) {
            try {
                println("Choose a card to play (1-${cardsInHand.size}):")
                val s = readln()
                if (s == "exit") {
                    play = false
                    return
                }
                val n = s.toInt()
                cardsOnTable.add(cardsInHand.removeAt(n - 1))
            } catch (e: Exception) {
                continue
            }
            break
        }
    } else {
        if (computerCards.isEmpty()) computerCards = get(INIT_HAND)
        cardsOnTable.add(computerCards.removeFirst())
        println("Computer plays ${cardsOnTable.last()}")
    }
    userTurn = !userTurn
    shuffle()
}

fun playFirst() {
    while (true) {
        println("Play first?")
        when (readln().lowercase()) {
            "yes" -> return
            "no" -> {
                userTurn = false
                return
            }
        }
    }
}

fun get(number: Int): MutableList<String> {
    val result = mutableListOf<String>()
    for (i in 1..number) result.add(currentDeck.removeFirst())
    return result
}

fun shuffle() {
    currentDeck.shuffled()
}