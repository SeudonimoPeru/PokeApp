package com.jhon.data.model.bean.locationArea

data class LocationAreaResponse(
    val encounter_method_rates: List<EncounterMethodRate>,
    val game_index: Int,
    val id: Int,
    val location: Location,
    val name: String,
    val names: List<Name>,
    val pokemon_encounters: List<PokemonEncounter>
)