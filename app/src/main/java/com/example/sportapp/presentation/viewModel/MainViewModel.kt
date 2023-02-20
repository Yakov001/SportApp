package com.example.sportapp.presentation.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.data.SportRepoImpl
import com.example.sportapp.domain.*
import com.example.sportapp.domain.entities.Game
import com.example.sportapp.domain.use_case.DeleteGameUseCase
import com.example.sportapp.domain.use_case.GetSavedGamesUseCase
import com.example.sportapp.domain.use_case.RequestAllGamesUseCase
import com.example.sportapp.domain.use_case.SaveGameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(val app: Application) : AndroidViewModel(application = app) {

    private val repository = SportRepoImpl.getInstance(app)
    private val requestAllGamesUseCase = RequestAllGamesUseCase(repository)
    private val getSavedGamesUseCase = GetSavedGamesUseCase(repository)
    private val saveGameUseCase = SaveGameUseCase(repository)
    private val deleteGameUseCase = DeleteGameUseCase(repository)

    var allGamesResponse = MutableStateFlow<Resource<List<List<Game>>>>(Resource.Idle())
        private set

    fun requestAllGames() {
        viewModelScope.launch (Dispatchers.IO) {
            allGamesResponse.value = Resource.Loading()
            allGamesResponse.value = requestAllGamesUseCase()
        }
    }

    var currentGame : Game? = null

    var savedGames = mutableStateListOf<Game>()
        private set

    fun saveGame(game: Game) {
        viewModelScope.launch (Dispatchers.IO) {
            saveGameUseCase(game)
            savedGames.add(game)
        }
        enableOneSignalNotification(game.timeStamp)
    }

    fun getSavedGames() {
        viewModelScope.launch (Dispatchers.IO) {
            savedGames.clear()
            savedGames.addAll(getSavedGamesUseCase())
        }
    }

    fun deleteGame(game: Game) {
        viewModelScope.launch (Dispatchers.IO) {
            deleteGameUseCase(game)
            savedGames.remove(game)
        }
    }

    fun enableOneSignalNotification(date : Int) {
        // OneSignal.sendTag("Game Start", date.toString())
    }
}