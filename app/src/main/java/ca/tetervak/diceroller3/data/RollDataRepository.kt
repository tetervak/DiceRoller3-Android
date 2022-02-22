package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData

interface RollDataRepository {
    suspend fun getRandomRollData(): RollData
}