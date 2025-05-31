package com.example.kuitandroidapiexample.ui.home.uistate

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.ui.model.AnimalType

data class AnimalListUiState(
    val animals: List<AnimalItemUiState> = emptyList()
)

data class AnimalItemUiState(
    val id: Int,
    val url: String = "",
    val animalName: String = "",
    val animalType: AnimalType = AnimalType.PROTECT,
    val address: String = "",
    val reporterName: String = ""
)
