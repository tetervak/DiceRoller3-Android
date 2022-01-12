package ca.tetervak.diceroller3.repository

import ca.tetervak.diceroller3.domain.HistoryItem

class GameDataRepository private constructor() {

    private var autoId = 0;
    private val items = HashMap<Int, HistoryItem>()

    @Synchronized
    fun save(item: HistoryItem){
        item.id = ++autoId;
        items[autoId] = item
    }

    // it gives a copy of the stored list
    @Synchronized
    fun getHistory(): List<HistoryItem>
        = items.values.toList().sortedBy { item -> item.id }

    @Synchronized
    fun delete(id: Int){
        items.remove(id)
    }

    @Synchronized
    fun clearAll(){
        items.clear()
    }

    companion object {

        @Volatile
        private var INSTANCE: GameDataRepository? = null

        fun getRepository(): GameDataRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameDataRepository().also {
                    INSTANCE = it
                }
            }
        }
    }

}