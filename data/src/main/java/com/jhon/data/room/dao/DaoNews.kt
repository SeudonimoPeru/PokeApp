package com.jhon.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.jhon.data.room.entity.RoomNews
// funciones especificas aparte de las heredadas
@Dao
interface DaoNews : DaoBase<RoomNews> {
    @Query("DELETE FROM News")
    fun deleteAll()

    @Query("SELECT * FROM News LIMIT 1")
    fun get(): RoomNews?

    @Query("SELECT * FROM News WHERE objectID = (:value)")
    operator fun get(value: String): RoomNews?

    @get:Query("SELECT * FROM News where visible = 1")
    val list: List<RoomNews>

    @get:Query("SELECT * FROM News where visible = 1")
    val liveDataList: LiveData<List<RoomNews>>

    @Query("UPDATE News SET visible = 0  WHERE objectID =:objectID")
    fun updateVisibiilty(objectID: String)
}