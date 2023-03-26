package indigo

import java.lang.Exception

const val TITLE = "Indigo Card Game"
const val INIT_TABLE = 4
const val INIT_HAND = 6
const val COMP_IND = 1
const val PLAYER_IND = 0
const val ADD_SCORE = 3
var lastWinCards = PLAYER_IND
var cardsOnTable = mutableListOf<String>()
val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
val suits = listOf("♦", "♥", "♠", "♣")
val deck = buildList {
    for (i in suits) {
        for (j in ranks) add("$j$i")
    }
}
var currentDeck = deck.toMutableList()
var play = true
var index = PLAYER_IND
var firstInd = PLAYER_IND
var users: List<User> = listOf(Player(), Computer())

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
    shuffle()
}

fun turn() {
    println(if (cardsOnTable.isEmpty()) "No cards on the table" else "${cardsOnTable.size} cards on the table, and the top card is ${cardsOnTable.last()}")
    if (currentDeck.isEmpty() && users[index].cards.isEmpty()) {
        play = false
        users[lastWinCards].winCards(cardsOnTable)
        finalScore()
        printScores(false)
        return
    }
    try {
        users[index].turn()
    } catch (e: Exception) {
        return
    }
    if (checkWinCards()) {
        lastWinCards = index
        users[index].winCards(cardsOnTable)
        cardsOnTable.clear()
        printScores()
    }
    index++
    index %= 2
    shuffle()
}

fun finalScore() {
    var index = PLAYER_IND
    if (users[COMP_IND].winedCardsCount == users[PLAYER_IND].winedCardsCount) index = firstInd
    else if (users[COMP_IND].winedCardsCount > users[PLAYER_IND].winedCardsCount) index = COMP_IND
    users[index].score += ADD_SCORE
}

fun checkWinCards(): Boolean {
    return cardsOnTable.size > 1 &&
            (cardsOnTable.last()[0] == cardsOnTable[cardsOnTable.size - 2][0] ||
                    cardsOnTable.last()[cardsOnTable.last().length - 1] ==
                    cardsOnTable[cardsOnTable.size - 2][cardsOnTable[cardsOnTable.size - 2].length - 1])
}

fun printScores(isNotFinal: Boolean = true) {
    if (isNotFinal) println("${users[index].name} wins cards")
    println(
        """Score: Player ${users[PLAYER_IND].score} - Computer ${users[COMP_IND].score}
Cards: Player ${users[PLAYER_IND].winedCardsCount} - Computer ${users[COMP_IND].winedCardsCount}"""
    )
}

fun playFirst() {
    while (true) {
        println("Play first?")
        when (readln().lowercase()) {
            "yes" -> return
            "no" -> {
                index = COMP_IND
                firstInd = COMP_IND
                lastWinCards = COMP_IND
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