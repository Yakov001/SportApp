package com.example.sportapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.model.Repo
import com.example.sportapp.model.data_classes.fixtures.Data
import com.example.sportapp.model.data_classes.fixtures.Fixtures
import com.example.sportapp.model.retrofit.SportApi
import com.example.sportapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

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

    var savedMatches = MutableStateFlow<MutableList<Data>>(mutableListOf())
        private set

    fun saveMatch(match: Data) {
        viewModelScope.launch (Dispatchers.IO) {
            Repo.getInstance().saveMatch(match, app.applicationContext)
            savedMatches.value.add(match)
        }
    }

    fun getSavedMatches() : List<Data> {
        var matches: List<Data>
        runBlocking(Dispatchers.IO) {
            matches = Repo.getInstance().getSavedMatches(app.applicationContext)
        }
        return matches
    }
}