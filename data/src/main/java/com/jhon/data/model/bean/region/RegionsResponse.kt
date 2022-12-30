package com.jhon.data.model.bean.region

data class RegionsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Region>
)