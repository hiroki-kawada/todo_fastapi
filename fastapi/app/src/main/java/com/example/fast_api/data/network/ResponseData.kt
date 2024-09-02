package com.example.fast_api.data.network

import com.google.gson.annotations.SerializedName

data class ResponseData(@SerializedName("test") val test: List<Item>)

data class Item(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class ResponseMessage(
    @SerializedName("message") val message: String,
)

