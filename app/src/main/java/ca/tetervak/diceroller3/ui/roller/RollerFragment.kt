package ca.tetervak.diceroller3.ui.roller

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.binding.bindDieImage
import ca.tetervak.diceroller3.binding.bindDieValue
import ca.tetervak.diceroller3.binding.bindGameTotal
import ca.tetervak.diceroller3.databinding.RollerFragmentBinding
import ca.tetervak.diceroller3.domain.Game
import com.google.android.material.snackbar.Snackbar

class RollerFragment : Fragment() {

    private var _binding: RollerFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RollerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RollerFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.game.observe(viewLifecycleOwner){ gameValue ->
            refresh(gameValue)
        }

        binding.rollButton.setOnClickListener {
            viewModel.roll()
        }

        binding.resetButton.setOnClickListener {
            viewModel.reset()
        }

        binding.saveButton.setOnClickListener {
            save()
        }

        binding.historyButton.setOnClickListener {
            showHistory()
        }
    }

    private fun refresh(gameValue: Game) {

        binding.game = gameValue

        bindDieValue(binding.die1TextView, gameValue.dice[0])
        bindDieValue(binding.die2TextView, gameValue.dice[1])
        bindDieValue(binding.die3TextView, gameValue.dice[2])

//        bindDieImage(binding.die1ImageView, gameValue.dice[0])
//        bindDieImage(binding.die2ImageView, gameValue.dice[1])
//        bindDieImage(binding.die3ImageView, gameValue.dice[2])

        bindGameTotal(binding.totalValueTextView, gameValue)
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