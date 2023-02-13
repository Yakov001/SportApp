package com.example.sportapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.sportapp.ui.compose.theme.SportAppTheme
import com.example.sportapp.viewModel.MainViewModel
import com.example.sportapp.databinding.FragmentSecondBinding
import com.example.sportapp.ui.compose.screens.LeaguesListScreen
import com.example.sportapp.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SportAppTheme {
                    val allFixturesResponse = viewModel.allFixturesResponse.collectAsState()
                    val savedMatches = viewModel.savedMatches

                    LeaguesListScreen(
                        listFixtures = allFixturesResponse.value,
                        onMatchClick = { data ->
                            viewModel.currentMatch = data
                            findNavController().navigate(R.id.action_SecondFragment_to_MatchFragment)
                        },
                        savedMatches = savedMatches
                    )
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}