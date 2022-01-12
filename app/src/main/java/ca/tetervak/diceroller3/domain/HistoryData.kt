package ca.tetervak.diceroller3.domain

data class HistoryData(
    val total: Int,
    val rolls: List<RollData>
){
    val count get() = rolls.size
}
