package com.jhon.data.model.bean.grupo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Grupo(
    var nombreGrupo: String?=null,
    var region: String? = null,
    var leader: String? = null,
    var isAuthor: Boolean? = false
)