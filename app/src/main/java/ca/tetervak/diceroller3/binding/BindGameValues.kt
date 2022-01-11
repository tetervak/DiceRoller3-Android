package ca.tetervak.diceroller3.binding

import android.widget.TextView
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.domain.Die
import ca.tetervak.diceroller3.domain.Game

fun bindDieValue(textView: TextView, die: Die?) {
    if (die is Die) {
        textView.text = if (die.isRolled) {
            die.value.toString()
        } else {
            textView.resources.getString(R.string.blank)
        }
    }
}

fun bindGameTotal(textView: TextView, game: Game?){
    if(game is Game){
        textView.text = String.format("%2d", game.total)
    }
}