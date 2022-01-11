package ca.tetervak.diceroller3.domain

import android.util.Log
import java.lang.IllegalArgumentException

class Die() {

    companion object{
        const val INIT_DIE_VALUE: Int = 1
    }

    var isRolled: Boolean = false
    private set

    // zero means not rolled yet
    var value: Int = INIT_DIE_VALUE
        set(n) {
            if (n in 1..6) {
                field = n
            } else {
                Log.e("Die", "Illegal die value $n")
                throw IllegalArgumentException("Illegal die value $n")
            }
        }

    constructor(n: Int) : this() {
        value = n
    }

    fun roll() {
        value = (1..6).random()
        isRolled = true
    }

    fun reset(){
        value = INIT_DIE_VALUE
        isRolled = false
    }
}