package ca.tetervak.diceroller3.binding

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.domain.HistoryItem
import ca.tetervak.diceroller3.ui.history.HistoryListAdapter

fun bindHistoryCountValue(textView: TextView, list: List<HistoryItem>?) {
    val count = list?.size ?: 0
    textView.text =
        textView.resources.getQuantityString(R.plurals.history_count, count, count)
}

fun bindHistoryListValues(recyclerView: RecyclerView, list: List<HistoryItem>?) {
    list?.let {
        val adapter = recyclerView.adapter as HistoryListAdapter
        adapter.submitList(it)
    }
}