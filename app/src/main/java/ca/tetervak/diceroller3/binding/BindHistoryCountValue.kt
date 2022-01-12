package ca.tetervak.diceroller3.binding

import android.widget.TextView
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.domain.HistoryItem

fun bindHistoryCountValue(textView: TextView, list: List<HistoryItem>?){
    val count = list?.size ?: 0
    textView.text =
        textView.resources.getQuantityString(R.plurals.history_count, count, count)
}