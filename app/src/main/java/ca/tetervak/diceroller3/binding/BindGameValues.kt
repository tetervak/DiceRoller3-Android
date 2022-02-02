package ca.tetervak.diceroller3.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.diceroller3.R

@BindingAdapter(value = ["dieValue", "isRolled"])
fun bindRollDieValue(textView: TextView, value: Int?, isRolled: Boolean?) {
    if (value is Int && isRolled is Boolean) {
        textView.text = if (isRolled) {
            value.toString()
        } else {
            textView.resources.getString(R.string.blank)
        }
    }
}

@BindingAdapter(value = ["dieImage","isRolled"])
fun bindDieImage(imageView: ImageView, value: Int?, isRolled: Boolean?){
    if(value is Int && isRolled is Boolean){
        if(isRolled) {
            when (value) {
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
fun bindGameTotal(textView: TextView, total: Int?){
    if(total is Int){
        textView.text = String.format("%2d", total)
    }
}