package ca.tetervak.diceroller3.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.binding.bindHistoryCountValue
import ca.tetervak.diceroller3.databinding.HistoryFragmentBinding

class HistoryFragment : Fragment() {

    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = HistoryFragmentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)

        val adapter = HistoryListAdapter()
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()
    }

    private fun refresh() {
        val history = viewModel.getHistory()
        val adapter = binding.recyclerView.adapter as HistoryListAdapter
        adapter.submitList(history)
        bindHistoryCountValue(binding.historyCount, history)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                clear()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clear() {
        viewModel.clear()
        refresh()
    }

}