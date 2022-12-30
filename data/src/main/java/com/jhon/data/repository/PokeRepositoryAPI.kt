package com.jhon.data.repository

import com.jhon.data.http.PokeService
import com.jhon.data.model.bean.region.RegionsResponse

class PokeRepositoryAPI {
    //repo de http
    private val api by lazy { PokeService() }

    suspend fun getAllRegion(): RegionsResponse? {
        return api.getRegions()
    }

}