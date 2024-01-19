package dev.notoriouscoder4.newstimes.data.model

import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)
