package ca.tetervak.diceroller3.domain

class Game {

    companion object {
        const val NUM_OF_DICE: Int = 3
    }

    val dice = List(NUM_OF_DICE) { Die() }

    var isRolled: Boolean = false
    private set

    val total: Int
        get() {
            return if (isRolled) {
                var sum = 0
                for (die in dice) {
                    sum += die.value
                }
                sum
            } else 0
        }

    fun roll() {
        for (die in dice) {
            die.roll()
        }
        isRolled = true
    }

    fun reset() {
        for (die in dice) {
            die.reset()
        }
        isRolled = false
    }
}