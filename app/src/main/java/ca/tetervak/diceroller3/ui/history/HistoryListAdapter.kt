package ca.tetervak.diceroller3.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.binding.bindDateTime
import ca.tetervak.diceroller3.binding.bindItemCountValue
import ca.tetervak.diceroller3.binding.bindResultValues
import ca.tetervak.diceroller3.databinding.HistoryListItemBinding
import ca.tetervak.diceroller3.domain.HistoryItem

class HistoryListAdapter(
    private var onDelete: (HistoryItem) -> Unit
): ListAdapter<HistoryItem, HistoryListAdapter.ViewHolder>(HistoryItemDiffCallback())  {

    inner class ViewHolder(
        private val binding: HistoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(count: Int, item: HistoryItem) {

            bindItemCountValue(binding.itemCountValue, count)
            bindResultValues(binding.resultValues, item)
            bindDateTime(binding.timeStampValue, item.date)

            binding.deleteButton.setOnClickListener {
                onDelete(item)
            }
        }
    }

    class HistoryItemDiffCallback : DiffUtil.ItemCallback<HistoryItem>() {
        override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HistoryListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position + 1, getItem(position))
    }


}