package ca.tetervak.diceroller3.domain

import java.util.*

data class HistoryItem(
    val dieValues: List<Int>,
    val totalValue: Int,
    val date: Date
){
    constructor(game: Game):
            this(
                game.dice.map { die -> die.value },
                game.total,
                Date()
            )
}
