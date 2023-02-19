package com.example.sportapp.model

import android.content.Context
import android.util.Log
import com.example.sportapp.model.data_classes.fixtures.Data
import com.example.sportapp.model.data_classes.fixtures.Fixtures
import com.example.sportapp.model.retrofit.SportApi
import com.example.sportapp.model.room.MatchesDatabase
import com.example.sportapp.utils.Resource
import kotlinx.coroutines.*
import retrofit2.Response

class Repo private constructor() {

    suspend fun requestAllFixtures(): Resource<List<Fixtures>> {
        var result: Resource<MutableList<Fixtures>> = Resource.Loading()
        withContext(Dispatchers.IO) {
            val results: MutableList<Response<Fixtures>> = mutableListOf()
            val jobs = mutableListOf<Job>()
            roundsOfLeagues.forEach {
                jobs.add(
                    launch {
                        results.add(SportApi.getInstance().requestFixtures(roundId = it))
                    }
                )
            }
            jobs.joinAll()
            results.forEach {
                if (!it.isSuccessful) return@forEach
                if (result !is Resource.Success) {
                    result = Resource.Success(data = mutableListOf(it.body()!!))
                } else result.data!!.add(it.body()!!)
            }
        }
        return result as Resource<List<Fixtures>>
    }

    suspend fun saveMatch(match: Data, context: Context) {
        MatchesDatabase(context).getMatchesDao().saveMatch(match)
    }

    suspend fun getSavedMatches(context: Context): List<Data> {
        return MatchesDatabase(context).getMatchesDao().getSavedMatches()
    }

    suspend fun deleteMatch(match: Data, context: Context) {
        MatchesDatabase(context).getMatchesDao().deleteMatch(match)
    }

    companion object {
        var repo: Repo? = null
        fun getInstance(): Repo {
            if (repo == null) {
                repo = Repo()
            }
            return repo!!
        }

        //superliga, aLeague, TipicoBundesliga
        val roundsOfLeagues = listOf(151633, 157592, 152556)
    }
}