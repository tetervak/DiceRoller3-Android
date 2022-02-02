package ca.tetervak.diceroller3.ui.roller

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.databinding.RollerFragmentBinding
import ca.tetervak.diceroller3.ui.util.EventObserver
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

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rollButton.setOnClickListener {
            viewModel.roll()
        }

        binding.resetButton.setOnClickListener {
            viewModel.reset()
        }

        viewModel.messageText.observe(viewLifecycleOwner, EventObserver{ message ->
            Snackbar.make(
                binding.root,
                getString(message),
                Snackbar.LENGTH_SHORT
            ).show()
        })
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
            R.id.action_history -> {
                showHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}