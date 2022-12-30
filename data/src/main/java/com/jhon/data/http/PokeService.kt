package com.jhon.data.http

import com.jhon.data.model.bean.region.RegionsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokeService {
    private val retrofit = RetrofitHelper.getRetrofit()


    suspend fun getRegions(): RegionsResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiApp::class.java).getAllRegion()
            response.body()
        }
    }

    suspend fun getLocation(idLocation: String): RegionsResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiApp::class.java).getLocation(idLocation)
            response.body()
        }
    }

    suspend fun getLocationArea(idLocationArea: String): RegionsResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokeApiApp::class.java).getLocation(idLocationArea)
            response.body()
        }
    }
}