package com.jhon.data.repository

import com.jhon.data.http.PokeService
import com.jhon.data.model.bean.InfoRegionResponse.InfoRegionResponse
import com.jhon.data.model.bean.PokedexResponse.PokedexResponse
import com.jhon.data.model.bean.region.RegionsResponse

class PokeRepositoryAPI {
    //repo de http
    private val api by lazy { PokeService() }

    suspend fun getAllRegion(): RegionsResponse? {
        return api.getRegions()
    }

    suspend fun getInfoRegion(idRegion : String): InfoRegionResponse? {
        return api.getInfoRegion(idRegion)
    }

    suspend fun getPokedex(idPokedex : String): PokedexResponse? {
        return api.getPokedex(idPokedex)
    }

}