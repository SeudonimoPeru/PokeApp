package com.jhon.data.model.bean.InfoRegionResponse

data class InfoRegionResponse(
    val id: Int,
    val locations: List<Location>,
    val main_generation: MainGeneration,
    val name: String,
    val names: List<Name>,
    val pokedexes: List<Pokedexe>,
    val version_groups: List<VersionGroup>
)