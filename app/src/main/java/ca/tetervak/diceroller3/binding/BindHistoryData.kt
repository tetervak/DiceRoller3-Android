package ca.tetervak.diceroller3.binding

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.domain.RollData
import ca.tetervak.diceroller3.ui.history.HistoryListAdapter

fun bindHistoryCountValue(textView: TextView, count: Int?) {
    if(count is Int){
        textView.text =
            textView.resources.getQuantityString(R.plurals.history_count, count, count)
    }
}

fun bindHistoryTotalValue(textView: TextView, total: Int?) {
    if(total is Int){
        textView.text =
            textView.resources.getQuantityString(R.plurals.history_total, total, total)
    }
}

fun bindHistoryListValues(recyclerView: RecyclerView, list: List<RollData>?) {
    list?.let {
        val adapter = recyclerView.adapter as HistoryListAdapter
        adapter.submitList(it)
    }
}

fun bindItemCountValue(textView: TextView, count: Int?) {
    if (count is Int) {
        textView.text =
            textView.resources.getString(R.string.count_value, count)
    }
}

fun bindResultValues(textView: TextView, item: RollData?) {
    item?.let {
        val values = item.values
        textView.text =
            textView.resources.getString(
                R.string.result_values, values[0], values[1], values[2], item.total
            )
    }
}