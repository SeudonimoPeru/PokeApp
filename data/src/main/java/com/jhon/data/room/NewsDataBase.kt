package com.jhon.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jhon.data.room.dao.DaoNews
import com.jhon.data.room.entity.RoomNews

@Database(
    entities = [
        RoomNews::class
    ],
    version = 1
)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun DaoNews(): DaoNews

    companion object {
        @Volatile
        private var INSTANCE: NewsDataBase? = null

        fun getDatabase(context: Context): NewsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDataBase::class.java,
                    "NewsDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}