package com.example.sportapp.data

import android.app.Application
import android.util.Log
import com.example.sportapp.data.network.entities.fixtures.FixturesDto
import com.example.sportapp.data.network.SportApi
import com.example.sportapp.data.database.GameDatabase
import com.example.sportapp.data.mapper.GameMapper
import com.example.sportapp.domain.Resource
import com.example.sportapp.domain.SportRepository
import com.example.sportapp.domain.entities.Game
import kotlinx.coroutines.*
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class SportRepoImpl private constructor(
    private val app: Application
) : SportRepository {

    private val matchesDao = GameDatabase(app).getGameDao()
    private val mapper = GameMapper()

    override suspend fun requestAllGames(): Resource<List<List<Game>>> {
        var result: Resource<MutableList<List<Game>>> = Resource.Loading()

        withContext(Dispatchers.IO) {

            val results: MutableList<Response<FixturesDto>> = mutableListOf()
            val jobs = mutableListOf<Job>()
            roundsOfLeagues.forEach {
                jobs.add(
                    launch {
                        try {
                            results.add(SportApi.getInstance().requestFixtures(roundId = it))
                        } catch (e: IOException) {
                            result = Resource.Error(message = e.message ?: "Internet Issues")
                            Log.d(NETWORK_TAG, "${e.javaClass} - ${e.message}")
                        } catch (e: Exception) {
                            result = Resource.Error(message = e.message ?: "Internet Issues")
                            Log.d(NETWORK_TAG, "${e.javaClass} - ${e.message}")
                        }
                    }
                )
            }
            jobs.joinAll()
            results.forEach { response ->
                if (!response.isSuccessful) return@forEach
                if (result !is Resource.Success) {
                    result = Resource.Success(data = mutableListOf(response.body()!!.data.map {
                        mapper.mapDtoToEntity(it)
                    }))
                } else result.data!!.add(response.body()!!.data.map { mapper.mapDtoToEntity(it) })
            }
        }
        return result as Resource<List<List<Game>>>
    }

    override suspend fun getSavedGames(): List<Game> {
        return matchesDao.getSavedGames().map { mapper.mapDbModelToEntity(it) }
    }

    override suspend fun saveGame(game: Game) {
        matchesDao.saveGame(
            mapper.mapEntityToDbModel(game)
        )
    }

    override suspend fun deleteGame(game: Game) {
        matchesDao.deleteGame(
            mapper.mapEntityToDbModel(game)
        )
    }

    companion object {
        private var repo: SportRepoImpl? = null
        fun getInstance(app: Application): SportRepoImpl {
            if (repo == null) {
                repo = SportRepoImpl(app)
            }
            return repo!!
        }

        //superliga, aLeague, TipicoBundesliga
        val roundsOfLeagues = listOf(151633, 157592, 152556)

        const val NETWORK_TAG = "NETWORK_EXCEPTION"
    }
}