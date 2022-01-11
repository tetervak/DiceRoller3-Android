package ca.tetervak.diceroller3.ui.roller

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.binding.bindDieValue
import ca.tetervak.diceroller3.binding.bindGameTotal
import ca.tetervak.diceroller3.databinding.RollerFragmentBinding
import com.google.android.material.snackbar.Snackbar

class RollerFragment : Fragment() {

    private var _binding: RollerFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RollerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RollerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh() // display the data from the game

        binding.rollButton.setOnClickListener {
            viewModel.roll()
            refresh()
        }

        binding.resetButton.setOnClickListener {
            viewModel.reset()
            refresh()
        }

        binding.saveButton.setOnClickListener {
            save()
        }

        binding.historyButton.setOnClickListener {
            showHistory()
        }
    }

    private fun refresh() {
        bindDieValue(binding.die1TextView, viewModel.game.dice[0])
        bindDieValue(binding.die2TextView, viewModel.game.dice[1])
        bindDieValue(binding.die3TextView, viewModel.game.dice[2])
        bindGameTotal(binding.totalValueTextView, viewModel.game)
    }

    private fun showHistory() {
        findNavController().navigate(R.id.action_roller_to_history)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_roller, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                save()
                true
            }
            R.id.action_history -> {
                showHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun save() {
        if(viewModel.save()){
            Snackbar.make(
                binding.root,
                getString(R.string.the_data_is_saved),
                Snackbar.LENGTH_SHORT
            ).show()
        }else{
            Snackbar.make(
                binding.root,
                getString(R.string.nothing_to_save),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}