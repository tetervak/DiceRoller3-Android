package ca.tetervak.diceroller3.domain

// All saved rolls
data class HistoryData(
    val rolls: List<RollData>
) {
    val count get() = rolls.size

    val total: Int

    init {
        var sum = 0;
        for (item in rolls) {
            sum += item.total
        }
        total = sum
    }
}
