package com.example.fast_api.data.network

import com.google.gson.annotations.SerializedName

data class RequestData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
