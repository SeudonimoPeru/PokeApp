package com.jhon.data.model.bean.locationArea

data class VersionDetailX(
    val encounter_details: List<EncounterDetail>,
    val max_chance: Int,
    val version: Version
)