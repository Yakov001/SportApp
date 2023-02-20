package com.example.sportapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportapp.R
import com.example.sportapp.databinding.FragmentFirstBinding
import com.example.sportapp.presentation.compose.screens.SplashScreen
import com.example.sportapp.presentation.compose.theme.SportAppTheme
import com.example.sportapp.presentation.viewModel.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SportAppTheme {
                    SplashScreen(
                        onSplashComplete = {
                            val navController = findNavController()
                            navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                        }
                    )
                }
            }
        }
        // Make API call while loading
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.requestAllGames()
        viewModel.getSavedGames()

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}