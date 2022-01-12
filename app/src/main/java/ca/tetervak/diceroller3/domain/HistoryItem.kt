package ca.tetervak.diceroller3.domain

import java.util.*

data class HistoryItem(
    var id: Int?,
    val dieValues: List<Int>,
    val totalValue: Int,
    val date: Date
){
    constructor(game: Game):
            this(
                null,
                game.dice.map { die -> die.value },
                game.total,
                Date()
            )
}
