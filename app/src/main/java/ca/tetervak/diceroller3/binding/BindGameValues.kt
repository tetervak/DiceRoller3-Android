package ca.tetervak.diceroller3.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.model.Die
import ca.tetervak.diceroller3.model.Game

@BindingAdapter("dieValue")
fun bindDieValue(textView: TextView, die: Die?) {
    if (die is Die) {
        textView.text = if (die.isRolled) {
            die.value.toString()
        } else {
            textView.resources.getString(R.string.blank)
        }
    }
}

@BindingAdapter("dieImage")
fun bindDieImage(imageView: ImageView, die: Die?){
    if(die is Die){
        if(die.isRolled) {
            when (die.value) {
                1 -> imageView.setImageResource(R.drawable.dice_1)
                2 -> imageView.setImageResource(R.drawable.dice_2)
                3 -> imageView.setImageResource(R.drawable.dice_3)
                4 -> imageView.setImageResource(R.drawable.dice_4)
                5 -> imageView.setImageResource(R.drawable.dice_5)
                6 -> imageView.setImageResource(R.drawable.dice_6)
            }
        }else{
            imageView.setImageResource(android.R.color.transparent)
        }
    }
}

@BindingAdapter("gameTotal")
fun bindGameTotal(textView: TextView, game: Game?){
    if(game is Game){
        textView.text = String.format("%2d", game.total)
    }
}