package com.example.kuitandroidapiexample.data.dto.request

import com.example.kuitandroidapiexample.ui.model.AnimalType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAddAnimalDto(
    @SerialName("data")
    val data: RequestDto
)

@Serializable
data class RequestDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("state")
    val state: AnimalType,
    @SerialName("breed")
    val breed: String,
    @SerialName("address")
    val address: String
)