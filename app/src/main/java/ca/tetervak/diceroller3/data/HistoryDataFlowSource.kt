package ca.tetervak.diceroller3.data

import android.util.Log
import ca.tetervak.diceroller3.model.HistoryData
import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

class HistoryDataFlowSource {

    companion object{
        const val TAG = "HistoryDataFlowSource"
    }

    private val dataSource = HistoryDataSource()

    private fun getHistoryData(): HistoryData {
        return dataSource.getHistoryData()
    }

    private val subscriptions = HashMap<String, HistoryDataSubscription>()

    private fun addHistoryListener(
        onUpdate: (HistoryData) -> Unit
    ): HistoryDataSubscription {
        Log.d(TAG, "addHistoryListener: called")
        val subscription = HistoryDataSubscription(onUpdate)
        synchronized(subscriptions){
            subscriptions.put(subscription.id, subscription)
        }
        subscription.onUpdate(getHistoryData())
        return subscription
    }

    fun removeSubscription(subscription: HistoryDataSubscription){
        Log.d(TAG, "removeSubscription: called")
        synchronized(subscription){
            subscriptions.remove(subscription.id)
        }
    }

    inner class HistoryDataSubscription(
        val onUpdate: (HistoryData) -> Unit
    ){
        val id = UUID.randomUUID().toString()

        fun remove(){
            removeSubscription(this)
        }
    }

    private fun refresh(){
        Log.d(TAG, "refresh: called")
        if(subscriptions.isNotEmpty()) {
            val history = getHistoryData()
            subscriptions.values.forEach { subscription ->
                subscription.onUpdate(history)
            }
        }
    }

    suspend fun saveRoll(rollData: RollData) {
        Log.d(TAG, "saveRoll: called")
        delay(100) // fake delay
        dataSource.saveRoll(rollData)
        refresh()
    }

    suspend fun deleteRoll(id: Int) {
        Log.d(TAG, "deleteRoll: called")
        delay(100) // fake delay
        dataSource.deleteRoll(id)
        refresh()
    }

    suspend fun clearAllRolls() {
        Log.d(TAG, "clearAllRolls: called")
        delay(100) // fake delay
        dataSource.clearAllRolls()
        refresh()
    }

    @ExperimentalCoroutinesApi
    fun getHistoryDataFlow(): Flow<HistoryData> {
        Log.d(TAG, "getHistoryDataFlow: called")

        val flow = callbackFlow {

            val subscription = addHistoryListener { history ->
                Log.d(TAG, "getHistoryDataFlow: listener called")
                trySend(history)
            }

            awaitClose {
                Log.d(TAG, "getHistoryDataFlow: awaitClose called")
                subscription.remove()
            }
        }
        return flow
    }
}