package ca.tetervak.diceroller3.data

import android.util.Log
import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

class RollDataFlowSource {

    companion object{
        const val TAG = "RollDataFlowSource"
    }

    private val dataSource = RollDataSource()

    private val subscriptions = HashMap<String, RollDataSubscription>()

    private fun addRollListener(
        onUpdate: (RollData) -> Unit
    ): RollDataSubscription {
        Log.d(TAG, "addRollListener: called")
        val subscription = RollDataSubscription(onUpdate)
        synchronized(subscriptions){
            subscriptions.put(subscription.id, subscription)
        }
        subscription.onUpdate(dataSource.getRollData())
        return subscription
    }

    fun removeSubscription(subscription: RollDataSubscription){
        Log.d(TAG, "removeSubscription: called")
        synchronized(subscription){
            subscriptions.remove(subscription.id)
        }
    }

    inner class RollDataSubscription(
        val onUpdate: (RollData) -> Unit
    ){
        val id = UUID.randomUUID().toString()

        fun remove(){
            removeSubscription(this)
        }
    }

    private fun refresh(){
        Log.d(TAG, "refresh: called")
        val rollData = dataSource.getRollData()
        subscriptions.values.forEach {
                subscription -> subscription.onUpdate(rollData)
        }
    }

    suspend fun rollDice(){
        Log.d(TAG, "rollDice: called")
        delay(100) // fake delay
        dataSource.rollDice()
        refresh()
    }

    @ExperimentalCoroutinesApi
    fun getRollDataFlow() : Flow<RollData> {

        val flow = callbackFlow {

            val subscription = addRollListener { rollData ->
                Log.d(TAG, "getHistoryDataFlow: listener called")
                trySend(rollData)
            }

            awaitClose {
                Log.d(TAG, "getHistoryDataFlow: awaitClose called")
                subscription.remove()
            }
        }

        return flow
    }
}