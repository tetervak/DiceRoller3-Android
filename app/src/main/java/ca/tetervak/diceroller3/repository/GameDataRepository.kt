package ca.tetervak.diceroller3.repository

import ca.tetervak.diceroller3.domain.HistoryItem

class GameDataRepository private constructor() {

    private val items = ArrayList<HistoryItem>()

    fun save(item: HistoryItem){
        items.add(item)
    }

    fun delete(id: Int){
        items.removeAt(id)
    }

    fun getCount() = items.size

    fun getHistory() = items

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