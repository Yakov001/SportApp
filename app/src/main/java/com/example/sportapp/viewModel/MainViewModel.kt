package com.example.sportapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.model.Repo
import com.example.sportapp.model.data_classes.fixtures.Data
import com.example.sportapp.model.data_classes.fixtures.Fixtures
import com.example.sportapp.model.retrofit.SportApi
import com.example.sportapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var fixturesResponse = MutableStateFlow<Resource<Fixtures>>(Resource.Idle())
        private set

    fun requestFixtures() {
        fixturesResponse.value = Resource.Loading()
        viewModelScope.launch {
            val response = try {
                SportApi.getInstance().requestFixtures()
            } catch (e: Exception) {
                Log.e("retrofit exception", e.message.toString())
                fixturesResponse.value = Resource.Error(e.message.toString())
                return@launch
            }

            if (response.isSuccessful) fixturesResponse.value = Resource.Success(response.body()!!)
            else {
                response.errorBody().toString().also {
                    Log.d("response", it)
                    fixturesResponse.value = Resource.Error(message = it)
                }
            }
        }
    }

    var allFixturesResponse = MutableStateFlow<Resource<List<Fixtures>>>(Resource.Idle())
        private set

    fun requestAllFixtures() {
        viewModelScope.launch (Dispatchers.IO) {
            allFixturesResponse.value = Resource.Loading()
            allFixturesResponse.value = Repo.getInstance().requestAllFixtures()
        }
    }

    var currentMatch : Data? = null

    fun bookMark(match: Data) {
        viewModelScope.launch (Dispatchers.IO) {
            Repo.getInstance().bookMark(match)
        }
    }
}