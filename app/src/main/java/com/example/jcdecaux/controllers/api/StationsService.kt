package com.example.jcdecaux.controllers.api

import com.example.jcdecaux.models.Station
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationsService {
    @GET("stations")
    fun getCergyStations(
        @Query("contract") contract: String,
        @Query("apiKey") apiKey: String
    ): Call<List<Station>>
}