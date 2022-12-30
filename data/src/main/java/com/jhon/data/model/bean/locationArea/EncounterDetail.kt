package com.jhon.data.model.bean.locationArea

data class EncounterDetail(
    val chance: Int,
    val condition_values: List<ConditionValue>,
    val max_level: Int,
    val method: Method,
    val min_level: Int
)