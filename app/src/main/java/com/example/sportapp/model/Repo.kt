package com.example.sportapp.model

import android.content.Context
import android.util.Log
import com.example.sportapp.model.data_classes.fixtures.Data
import com.example.sportapp.model.data_classes.fixtures.Fixtures
import com.example.sportapp.model.retrofit.SportApi
import com.example.sportapp.model.room.MatchesDatabase
import com.example.sportapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class Repo private constructor(){

    suspend fun requestAllFixtures() : Resource<List<Fixtures>> {
        var result: Resource<MutableList<Fixtures>> = Resource.Loading()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val results : MutableList<Response<Fixtures>> = mutableListOf()
                roundsOfLeagues.forEach {
                    results.add(
                        async{ SportApi.getInstance().requestFixtures(roundId = it) }.await()
                    )
                }
                results.forEach {
                    if (result !is Resource.Success) {
                        result = Resource.Success(data = mutableListOf(it.body()!!))
                    } else result.data!!.add(it.body()!!)
                }
            }.join()
        } catch (e : Exception) {
            Log.d("Repo", e.message ?: ":(")
            result = Resource.Error("Failed request")
        }
        return result as Resource<List<Fixtures>>
    }

    suspend fun saveMatch(match: Data, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            MatchesDatabase(context).getMatchesDao().saveMatch(match)
        }
    }

    suspend fun getSavedMatches(context: Context) : List<Data> {
        var matches = listOf<Data>()
        CoroutineScope(Dispatchers.IO).launch {
            matches = async{ MatchesDatabase(context).getMatchesDao().getSavedMatches() }.await()
        }.join()
        return matches
    }

    suspend fun deleteMatch(match: Data, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            MatchesDatabase(context).getMatchesDao().deleteMatch(match)
        }
    }

    companion object {
        var repo: Repo? = null
        fun getInstance() : Repo {
            if (repo == null) {
                repo = Repo()
            }
            return repo!!
        }

                              //superliga, aLeague, TipicoBundesliga
        val roundsOfLeagues = listOf(151633, 157592, 152556)
    }
}