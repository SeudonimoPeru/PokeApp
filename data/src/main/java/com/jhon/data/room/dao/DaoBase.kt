package com.jhon.data.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
// interface se hereda ppara cualquier dao
interface DaoBase<T> {

    @Delete
    fun delete(entity: T)

    @Insert
    fun insert(vararg entitys: T): List<Long>

    @Insert
    fun insert(entitys: List<T>): List<Long>

    @Insert
    fun insert(entity: T): Long

    @Update
    fun update(entity: T): Int

    @Update
    fun update(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entitys: T)
}