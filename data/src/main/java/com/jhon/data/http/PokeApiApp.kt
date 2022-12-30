package com.jhon.data.http

import com.jhon.data.model.bean.region.RegionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiApp {


    // trae todas las localizaciones
    @GET("region")
    suspend fun getAllRegion(): Response<RegionsResponse>

    @GET("location/{idLocation}")
    suspend fun getLocation(
        @Path("idLocation") version: String
    ): Response<RegionsResponse>


    @GET("location/{idLocationArea}")
    suspend fun getLocationArea(
        @Path("idLocationArea") version: String
    ): Response<RegionsResponse>

}