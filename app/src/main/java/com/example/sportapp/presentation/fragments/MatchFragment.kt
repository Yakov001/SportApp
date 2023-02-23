package com.example.sportapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.sportapp.databinding.MatchFragmentBinding
import com.example.sportapp.presentation.compose.screens.match_screen.MatchScreen
import com.example.sportapp.presentation.compose.theme.SportAppTheme
import com.example.sportapp.presentation.viewModel.MainViewModel
import com.example.sportapp.R


class MatchFragment : Fragment() {


    private var _binding: MatchFragmentBinding? = null
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

        _binding = MatchFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val match = viewModel.currentGame!!

        binding.composeViewMatch.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SportAppTheme {
                    val savedMatches = viewModel.savedGames
                    val isSaved = savedMatches.contains(match)

                    MatchScreen(
                        game = match,
                        onBackClick = {
                            findNavController().navigate(R.id.action_MatchFragment_to_SecondFragment)
                            viewModel.currentGame = null
                        },
                        onMatchSave = {
                            if (isSaved) {
                                viewModel.deleteGame(match)
                            } else {
                                viewModel.saveGame(match)
                            }
                        },
                        isSaved = isSaved
                    ) { findNavController().navigate(R.id.action_MatchFragment_to_WebViewFragment) }
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