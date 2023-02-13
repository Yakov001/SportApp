package com.example.sportapp.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.model.Repo
import com.example.sportapp.model.data_classes.fixtures.Data
import com.example.sportapp.model.data_classes.fixtures.Fixtures
import com.example.sportapp.utils.Resource
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(val app: Application) : AndroidViewModel(application = app) {

    var allFixturesResponse = MutableStateFlow<Resource<List<Fixtures>>>(Resource.Idle())
        private set

    fun requestAllFixtures() {
        viewModelScope.launch (Dispatchers.IO) {
            allFixturesResponse.value = Resource.Loading()
            allFixturesResponse.value = Repo.getInstance().requestAllFixtures()
        }
    }

    var currentMatch : Data? = null

    var savedMatches = mutableStateListOf<Data>()
        private set

    fun saveMatch(match: Data) {
        viewModelScope.launch (Dispatchers.IO) {
            Repo.getInstance().saveMatch(match, app.applicationContext)
            savedMatches.add(match)
        }
        enableOneSignalNotification(match.time.timestamp)
    }

    fun getSavedMatches() {
        viewModelScope.launch (Dispatchers.IO) {
            savedMatches.clear()
            savedMatches.addAll(Repo.getInstance().getSavedMatches(app.applicationContext))
        }
    }

    fun deleteMatch(match: Data) {
        viewModelScope.launch (Dispatchers.IO) {
            Repo.getInstance().deleteMatch(match, app.applicationContext)
            savedMatches.remove(match)
        }
    }

    fun enableOneSignalNotification(date : Int) {
        // OneSignal.sendTag("Game Start", date.toString())
    }
}