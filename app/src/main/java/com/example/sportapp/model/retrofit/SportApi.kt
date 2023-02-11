package com.example.sportapp.model.retrofit

import com.example.sportapp.model.data_classes.Fixtures
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SportApi {

    @GET(API_KEY)
    suspend fun requestFixtures(
        @Query("t") type: String = "round",
        @Query("round_id") roundId: Int = 151633,
    ) : Response<Fixtures>

    companion object {
        var userApi: SportApi? = null
        fun getInstance() : SportApi {
            if (userApi == null) {
                userApi = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SportApi::class.java)
            }
            return userApi!!
        }
        private const val BASE_URL = "https://api.soccersapi.com/v2.2/fixtures/"
        private const val API_KEY = "?user=yakovrussian&token=3bbe8cb0d3dcd0ac73f2d77cffb03c55"
    }
}