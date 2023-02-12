package com.example.sportapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.sportapp.databinding.MatchFragmentBinding
import com.example.sportapp.ui.compose.screens.MatchScreen
import com.example.sportapp.ui.compose.theme.SportAppTheme
import com.example.sportapp.viewModel.MainViewModel
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

        val match = viewModel.currentMatch!!

        binding.composeViewMatch.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SportAppTheme {
                    MatchScreen(
                        match = match,
                        onBackClick = {
                            findNavController().navigate(R.id.action_MatchFragment_to_SecondFragment)
                            viewModel.currentMatch = null
                        },
                        onMatchSave = { viewModel.bookMark(match) })
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