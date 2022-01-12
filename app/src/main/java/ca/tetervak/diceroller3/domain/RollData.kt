package ca.tetervak.diceroller3.domain

import java.util.*

// The data from one roll
data class RollData(
    var id: Int?,
    val values: List<Int>,
    val total: Int,
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
