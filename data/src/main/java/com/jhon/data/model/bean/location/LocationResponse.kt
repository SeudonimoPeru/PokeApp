package com.jhon.data.model.bean.location

data class LocationResponse(
    val areas: List<Area>,
    val game_indices: List<Any>,
    val id: Int,
    val name: String,
    val names: List<Name>,
    val region: Region
)