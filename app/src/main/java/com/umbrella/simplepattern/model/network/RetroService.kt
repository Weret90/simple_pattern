package com.umbrella.simplepattern.model.network

import com.umbrella.simplepattern.model.SomeObject
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("")
    suspend fun getDataFromApi(
        @Query("parameter1") parameter1: String,
        @Query("parameter2") parameter2: String
    ): List<SomeObject>
}