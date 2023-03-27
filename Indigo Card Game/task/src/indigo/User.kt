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
        println(cards.joinToString(" "))
        val card = cardSelection()
        cards.remove(card)
        cardsOnTable.add(card)
        println("Computer plays ${cardsOnTable.last()}")
    }

    private fun cardSelection(): String {
        if (cards.size == 1) return cards[0]
        if (cardsOnTable.size > 0) {
            val firstCandidates = cards.filter {
                it.last() == cardsOnTable.last().last()
            }
            if (firstCandidates.size > 1) return firstCandidates[0]
            val secondCandidates = cards.filter {
                it.first() == cardsOnTable.last().first()
            }
            if (secondCandidates.size > 1) return secondCandidates[0]
            if (firstCandidates.isNotEmpty()) return firstCandidates[0]
            if (secondCandidates.isNotEmpty()) return secondCandidates[0]
        }
        for (suit in suits) {
            val suitCards = cards.filter { suit[0] == it.last() }.toMutableList()
            if (suitCards.size > 1) return suitCards[0]
        }
        val groupByRanks = cards.groupBy { it[0] }.values.filter { it.size > 1 }
        if (groupByRanks.isNotEmpty()) return groupByRanks[0][0]
        return cards[0]
    }
}