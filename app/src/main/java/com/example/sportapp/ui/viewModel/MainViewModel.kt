package com.example.sportapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.ui.model.Resource
import com.example.sportapp.ui.model.data_classes.Fixtures
import com.example.sportapp.ui.model.retrofit.SportApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var fixturesResponse = MutableStateFlow<Resource<Fixtures>>(Resource.Idle())
        private set

    fun getFixtures() {
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
}