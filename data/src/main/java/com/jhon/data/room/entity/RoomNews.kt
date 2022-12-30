package com.jhon.data.room.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
//entidades para room
@Entity(tableName = "News")
data class RoomNews(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var objectID: String = "",

    var author: String? = "",
    var created_at_i: Int? = 0,
    var created_at: String? = "",
    var story_title: String? = "",
    var story_url: String? = "",
    var visible: Boolean = true
)