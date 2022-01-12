package ca.tetervak.diceroller3.repository

import ca.tetervak.diceroller3.domain.HistoryData
import ca.tetervak.diceroller3.domain.RollData

class GameDataSource {

    private var autoId = 0;
    private val itemsMap = HashMap<Int, RollData>()

    private val itemsList: List<RollData>
        get() {
            var list: List<RollData>
            synchronized(itemsMap) {
                list = itemsMap.values.toList()
            }
            return list.sortedBy { item -> item.id }
        }

    fun saveRoll(item: RollData) {
        synchronized(itemsMap) {
            item.id = ++autoId;
            itemsMap[autoId] = item
        }
    }

    fun getHistoryData(): HistoryData {
        val list = itemsList
        var sum = 0;
        for (item in list) {
            sum += item.total
        }
        return HistoryData(total = sum, rolls = list)
    }

    fun deleteRoll(id: Int) {
        synchronized(itemsMap) {
            itemsMap.remove(id)
        }
    }

    fun clearAllRolls() {
        synchronized(itemsMap) {
            itemsMap.clear()
        }
    }
}