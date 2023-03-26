package indigo

val givePoint = listOf('1', 'A', 'J', 'Q', 'K')

open class User(val name: String) {
    var cards = mutableListOf<String>()
    var winedCardsCount = 0
    var score = 0

    fun winCards(winCards: MutableList<String>) {
        winedCardsCount += winCards.size
        winCards.forEach {
            if (givePoint.contains(it[0])) {
                score++
            }
        }
    }

    open fun turn() {
        if (cards.isEmpty()) cards = get(INIT_HAND)
    }
}

class Player : User("Player") {
    override fun turn() {
        super.turn()
        print("Cards in hand: ")
        (0 until cards.size).forEach {
            print("${it + 1})${cards[it]} ")
        }
        println()
        while (true) {
            try {
                println("Choose a card to play (1-${cards.size}):")
                val s = readln()
                if (s == "exit") {
                    play = false
                    throw Exception("exit")
                }
                val n = s.toInt()
                cardsOnTable.add(cards.removeAt(n - 1))
            } catch (e: Exception) {
                if (e.message == "exit") throw Exception()
                continue
            }
            break
        }
    }
}

class Computer : User("Computer") {
    override fun turn() {
        super.turn()
        cardsOnTable.add(cards.removeFirst())
        println("Computer plays ${cardsOnTable.last()}")

    }
}