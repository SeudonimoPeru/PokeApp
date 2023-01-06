package com.jhon.data.http

import com.jhon.data.model.bean.InfoRegionResponse.InfoRegionResponse
import com.jhon.data.model.bean.PokedexResponse.PokedexResponse
import com.jhon.data.model.bean.region.RegionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiApp {


    // trae todas las localizaciones
    @GET("region")
    suspend fun getAllRegion(): Response<RegionsResponse>


    @GET("region/{idRegion}")
    suspend fun getInfoRegion(
        @Path("idRegion") idRegion: String
    ): Response<InfoRegionResponse>


    @GET("pokedex/{idPokedex}")
    suspend fun getAllPokemonForRegion(
        @Path("idPokedex") idPokedex: String
    ): Response<PokedexResponse>

    @GET("location/{idLocation}")
    suspend fun getLocation(
        @Path("idLocation") version: String
    ): Response<RegionsResponse>


    @GET("location/{idLocationArea}")
    suspend fun getLocationArea(
        @Path("idLocationArea") version: String
    ): Response<RegionsResponse>

}